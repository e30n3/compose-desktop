package com.myapp.ui.feature.drawer.report


import com.myapp.actify.data.Interactor
import com.myapp.util.ViewModel
import com.myapp.util.extention.getCurrentDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.ReportBody
import ru.involta.actify.domain.entity.api.response.ReportResponse
import javax.inject.Inject


class ReportViewModel @Inject constructor(
  private val interactor: Interactor,
) : ViewModel() {

  private val _stateReport: MutableStateFlow<Result<List<ReportBody>>> =
    MutableStateFlow(Result.empty())
  val stateReport: StateFlow<Result<List<ReportBody>>> = _stateReport
  fun clearReport() {
    _stateReport.value = Result.empty()
  }

  fun report(date: String = getCurrentDate()) =
    viewModelScope.launch(Dispatchers.IO) {
      //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
      _stateReport.value = Result.loading()
      //delay(2000)
      interactor.report(date)
        .catch { e ->
          println(e.stackTraceToString())
          _stateReport.value =
            Result.error(Throwable("Проверьте подкючение к сети интернет: ${e.message}"))
        }.collect { data: ReportResponse? ->
          if (data != null && data.isSuccess) {
            println("SUCCESS!!!")
            _stateReport.value = Result.success(data.data)
          } else
            _stateReport.value =
              Result.error(Throwable(data?.message ?: "Проверьте подключение к сети инернет"))
        }
    }

}