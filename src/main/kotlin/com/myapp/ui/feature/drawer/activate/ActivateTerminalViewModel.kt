package com.myapp.ui.feature.drawer.activate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.myapp.actify.data.Interactor
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse
import javax.inject.Inject


class ActivateTerminalViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  var id by mutableStateOf("")
  var key by mutableStateOf("")


  private val _stateRegistration: MutableStateFlow<Result<TerminalRegistrationResponse>> =
    MutableStateFlow(Result.empty())
  val stateRegistration: StateFlow<Result<TerminalRegistrationResponse>> = _stateRegistration
  fun clearRegistration() {
    _stateRegistration.value = Result.empty()
  }

  fun clearViewModel() {
    clearRegistration()
    id = ""
    key = ""
  }

  fun registration() =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
      _stateRegistration.value = Result.loading()
      //delay(2000)
      interactor.terminalRegistration(id.toLongOrNull() ?: 0, key)
        .catch { e ->
          println(e.stackTraceToString())
          _stateRegistration.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: TerminalRegistrationResponse? ->
          if (data != null && data.isSuccess) {
            println("SUCCESS!!!")
            _stateRegistration.value = Result.success(data)
          } else
            _stateRegistration.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }

}