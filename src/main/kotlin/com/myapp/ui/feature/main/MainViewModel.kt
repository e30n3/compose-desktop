package com.myapp.ui.feature.main

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.myapp.actify.data.Interactor
import com.myapp.actify.di.AppComponent
import com.myapp.ui.feature.drawer.*
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.BalanceResponse
import ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse
import javax.inject.Inject

class MainViewModel @Inject constructor(
  private val interactor: Interactor,
  appComponent: AppComponent,
  // Inject your repos here...
) : ViewModel() {

  init {
    appComponent.inject(this)
  }

  private val _stateTerminals: MutableStateFlow<Result<TerminalRegistrationResponse>> =
    MutableStateFlow(Result.empty())
  val stateTerminals: StateFlow<Result<TerminalRegistrationResponse>> = _stateTerminals
  var isAuth by mutableStateOf(false)

  fun getTerminal() = viewModelScope.launch(Dispatchers.IO) {
    //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
    _stateTerminals.value = Result.loading()
    delay(3000)
    interactor.getTerminal()
      .catch { e ->
        println(e.stackTraceToString())
        _stateTerminals.value =
          Result.error(Throwable("Ошибка: ${e.message}"))
      }.collect { data: TerminalRegistrationResponse? ->
        if (data != null) {
          isAuth = true
          println("SUCCESS!!!")
          _stateTerminals.value = Result.success(data)
        } else {
          isAuth = false
          _stateTerminals.value =
            Result.error(Throwable("Ошибка"))
        }
      }
  }

  class RenderDrawer(appComponent: AppComponent) {

    init {
      appComponent.inject(this)
    }

    @Inject
    lateinit var reportViewModel: ReportViewModel

    @Inject
    lateinit var activateViewModel: ActivateTerminalViewModel

    @Inject
    lateinit var statusViewModel: TerminalStatusViewModel

    @Composable
    fun renderReport(modifier: Modifier = Modifier) {
      val scope = rememberCoroutineScope()
      LaunchedEffect(reportViewModel) {
        reportViewModel.init(scope)
      }

      ReportScreen(reportViewModel, modifier)
    }

    @Composable
    fun renderActivate(
      modifier: Modifier = Modifier,
      onSuccess: () -> Unit,
    ) {
      val scope = rememberCoroutineScope()
      LaunchedEffect(activateViewModel) {
        activateViewModel.init(scope)
      }
      ActivateTerminalScreen(activateViewModel, modifier, onSuccess)
    }

    @Composable
    fun renderStatus(
      modifier: Modifier = Modifier,
      onExit: () -> Unit
    ): () -> Unit {
      val scope = rememberCoroutineScope()
      LaunchedEffect(statusViewModel) {
        statusViewModel.init(scope)
      }
      return TerminalStatusScreen(modifier, statusViewModel, onExit)
    }
  }

  val innerViewModels = RenderDrawer(appComponent)

}