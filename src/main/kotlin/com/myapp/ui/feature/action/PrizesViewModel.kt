package com.myapp.ui.feature.action

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
import ru.involta.actify.domain.entity.api.response.PrizeBody
import ru.involta.actify.domain.entity.api.response.PrizeElement
import ru.involta.actify.domain.entity.api.response.PrizesResponse
import javax.inject.Inject


class PrizesViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  var code by mutableStateOf("")

  var selectedActionId by mutableStateOf<Long>(0)
  var selectedPrize by mutableStateOf<PrizeElement?>(null)
  var codeVisibility by mutableStateOf(false)


  private val _statePrizes: MutableStateFlow<Result<List<PrizeBody>>> =
    MutableStateFlow(Result.empty())
  val statePrizes: StateFlow<Result<List<PrizeBody>>> = _statePrizes
  fun clearPrizees() {
    _statePrizes.value = Result.empty()
  }

  private val _stateClaimPrize: MutableStateFlow<Result<ResponseBody>> =
    MutableStateFlow(Result.empty())
  val stateClaimPrize: StateFlow<Result<ResponseBody>> = _stateClaimPrize
  fun clearClaimPrize() {
    _stateClaimPrize.value = Result.empty()
  }


  fun prizes(cardOrPhone: String) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
      _statePrizes.value = Result.loading()
      //delay(2000)
      interactor.prizes(cardOrPhone)
        .catch { e ->
          println(e.stackTraceToString())
          _statePrizes.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: PrizesResponse? ->
          println(data)
          if (data != null && data.isSuccess) {
            _statePrizes.value = Result.success(data.data)
          } else
            _statePrizes.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }


  fun claimPrize(cardOrPhone: String) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
      _stateClaimPrize.value = Result.loading()
      codeVisibility = false
      //delay(2000)
      interactor.claimPrize(cardOrPhone, selectedActionId, selectedPrize?.prizeId ?: -1, code)
        .catch { e ->
          println(e.stackTraceToString())
          _stateClaimPrize.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: ResponseBody? ->
          if (data != null && data.isSuccess) {
            println("SUCCESS!!!")
            codeVisibility = (data._status == 2)
            _stateClaimPrize.value = Result.success(data)
          } else {
            codeVisibility = false
            _stateClaimPrize.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
          }
        }
    }
}