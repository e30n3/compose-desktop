package com.myapp.ui.feature.drawer

import com.myapp.actify.data.Interactor
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse
import javax.inject.Inject


class TerminalStatusViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  private val _stateTerminals: MutableStateFlow<Result<TerminalRegistrationResponse>> =
    MutableStateFlow(Result.empty())
  val stateTerminals: StateFlow<Result<TerminalRegistrationResponse>> = _stateTerminals

  fun logOut() = viewModelScope.launch(Dispatchers.IO) {
    //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
    _stateTerminals.value = Result.loading()
    //delay(2000)
    interactor.logOut()
      .catch { e ->
        println(e.stackTraceToString())
        _stateTerminals.value =
          Result.error(Throwable("Ошибка: ${e.message}"))
      }.collect {
        _stateTerminals.value =
          Result.error(Throwable("Ошибка"))
      }
  }

  fun getTerminal() =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
      _stateTerminals.value = Result.loading()
      //delay(2000)
      interactor.getTerminal()
        .catch { e ->
          println(e.stackTraceToString())
          _stateTerminals.value =
            Result.error(Throwable("Ошибка: ${e.message}"))
        }.collect { data: TerminalRegistrationResponse? ->
          if (data != null) {
            println("SUCCESS!!!")
            _stateTerminals.value = Result.success(data)
          } else
            _stateTerminals.value =
              Result.error(Throwable("Ошибка"))
        }
    }
}