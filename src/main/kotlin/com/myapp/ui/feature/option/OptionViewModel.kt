package com.myapp.ui.feature.option

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



class OptionViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  private val _stateBalance: MutableStateFlow<Result<BalanceResponse>> =
    MutableStateFlow(Result.empty())
  val stateBalance: StateFlow<Result<BalanceResponse>> = _stateBalance
  fun clearBalance() {
    _stateBalance.value = Result.empty()
  }

  fun balance(card: String) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
      _stateBalance.value = Result.loading()
      //delay(2000)
      interactor.getBalance(card)
        .catch { e ->
          println(e.stackTraceToString())
          _stateBalance.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: BalanceResponse? ->
          println(data)
          if (data != null && data.isSuccess) {
            _stateBalance.value = Result.success(data)
          } else
            _stateBalance.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }


}