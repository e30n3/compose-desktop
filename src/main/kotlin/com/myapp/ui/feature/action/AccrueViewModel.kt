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
import ru.involta.actify.domain.entity.api.response.BalanceResponse
import javax.inject.Inject
import kotlin.math.roundToLong


class AccrueViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  var amount by mutableStateOf("")

  private val _stateAccrue: MutableStateFlow<Result<BalanceResponse>> =
    MutableStateFlow(Result.empty())
  val stateAccrue: StateFlow<Result<BalanceResponse>> = _stateAccrue
  fun clearAccrue() {
    _stateAccrue.value = Result.empty()
  }

  fun accrue(cardOrPhone: String) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
      _stateAccrue.value = Result.loading()
      //delay(2000)
      interactor.accrue(cardOrPhone, amount.toDouble().times(100).roundToLong().toDouble().div(100f))
        .catch { e ->
          println(e.stackTraceToString())
          _stateAccrue.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: BalanceResponse? ->
          println(data)
          if (data != null && data.isSuccess) {
            _stateAccrue.value = Result.success(data)
          } else
            _stateAccrue.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }
}