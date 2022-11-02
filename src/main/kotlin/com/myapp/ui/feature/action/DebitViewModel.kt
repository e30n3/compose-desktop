package ru.involta.actify.ui.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.myapp.actify.data.Interactor
import com.myapp.util.ViewModel
import com.myapp.util.extention.prettyString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.ResponseBody
import ru.involta.actify.domain.entity.api.response.DebitCheckResponse
import javax.inject.Inject
import kotlin.math.roundToLong


class DebitViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  var amount by mutableStateOf("")
  var debit by mutableStateOf("")
  var code by mutableStateOf("")
  var codeVisibility by mutableStateOf(false)
  var lastCheckValue by mutableStateOf("")

  private val _stateCheck: MutableStateFlow<Result<DebitCheckResponse>> =
    MutableStateFlow(Result.empty())
  val stateCheck: StateFlow<Result<DebitCheckResponse>> = _stateCheck
  fun clearCheck() {
    _stateCheck.value = Result.empty()
  }

  private val _stateDebit: MutableStateFlow<Result<ResponseBody>> =
    MutableStateFlow(Result.empty())
  val stateDebit: StateFlow<Result<ResponseBody>> = _stateDebit
  fun clearDebit() {
    _stateDebit.value = Result.empty()
  }


  var debitJob: Job? = null

  fun debitCheck(cardOrPhone: String) {
    if (debitJob?.isActive == true) debitJob?.cancel()
    debitJob = viewModelScope.launch(Dispatchers.IO) {
      lastCheckValue = _stateCheck.value.data?.amount?.value?.prettyString ?: "⠀⠀⠀"
      //if (_stateCheck.value.status == Result.Status.ERROR) delay(5000)
      _stateCheck.value = Result.loading()
      //delay(2000)
      interactor.debitCheck(
        cardOrPhone,
        amount.toDoubleOrNull() ?: 0.0
      )
        .catch { e ->
          println(e.stackTraceToString())
          _stateCheck.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: DebitCheckResponse? ->
          println(data)
          if (data != null && data.isSuccess) {
            _stateCheck.value = Result.success(data)
          } else
            _stateCheck.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }
  }

  fun debit(cardOrPhone: String) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
      _stateDebit.value = Result.loading()
      //delay(2000)
      interactor.debit(
        cardOrPhone,
        amount.toDoubleOrNull()?.times(100)?.roundToLong()?.toDouble()?.div(100f) ?: 0.0,
        debit.toDoubleOrNull()?.times(100)?.roundToLong()?.toDouble()?.div(100f) ?: 0.0,
        code
      ).catch { e ->
        println(e.stackTraceToString())
        _stateDebit.value =
          Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
      }.collect { data: ResponseBody? ->
        println(data)
        if (data != null && data.isSuccess) {
          codeVisibility = data._status == 2
          _stateDebit.value = Result.success(data)
        } else {
          codeVisibility = false
          _stateDebit.value =
            Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
      }
    }
}