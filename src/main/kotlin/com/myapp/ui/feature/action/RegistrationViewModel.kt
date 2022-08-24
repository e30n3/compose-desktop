package ru.involta.actify.ui.screen.viewmodel

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
import ru.involta.actify.domain.entity.api.ResponseBody
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  var phone by mutableStateOf("")
  var code by mutableStateOf("")

  private val _stateSendSms: MutableStateFlow<Result<ResponseBody>> =
    MutableStateFlow(Result.empty())
  val stateSendSms: StateFlow<Result<ResponseBody>> = _stateSendSms
  fun clearSendSms() {
    _stateSendSms.value = Result.empty()
  }

  private val _stateRegistration: MutableStateFlow<Result<ResponseBody>> =
    MutableStateFlow(Result.empty())
  val stateRegistration: StateFlow<Result<ResponseBody>> = _stateRegistration
  fun clearRegistration() {
    _stateRegistration.value = Result.empty()
  }


  fun sendSms() =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
      _stateSendSms.value = Result.loading()
      //delay(2000)
      interactor.sendSms(1, phone)
        .catch { e ->
          println(e.stackTraceToString())
          _stateSendSms.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: ResponseBody? ->
          println(data)
          if (data != null && data.isSuccess) {
            _stateSendSms.value = Result.success(data)
          } else
            _stateSendSms.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }


  fun registration() =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
      _stateRegistration.value = Result.loading()
      //delay(2000)
      interactor.registration(code, phone)
        .catch { e ->
          println(e.stackTraceToString())
          _stateRegistration.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: ResponseBody? ->
          if (data != null && data.isSuccess) {
            println("SUCCESS!!!")
            _stateRegistration.value = Result.success(data)
          } else
            _stateRegistration.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }

}