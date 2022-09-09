package com.myapp.ui.feature.main

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.myapp.actify.data.Interactor
import com.myapp.actify.di.AppComponent
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse
import com.myapp.ui.feature.action.AccrueScreen
import com.myapp.ui.feature.action.DebitScreen
import com.myapp.ui.feature.action.PrizesViewModel
import com.myapp.ui.feature.drawer.activate.ActivateTerminalScreen
import com.myapp.ui.feature.drawer.activate.ActivateTerminalViewModel
import com.myapp.ui.feature.drawer.report.ReportScreen
import com.myapp.ui.feature.drawer.report.ReportViewModel
import com.myapp.ui.feature.option.OptionScreen
import com.myapp.ui.feature.option.OptionViewModel
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.screen.main.nested.*
import ru.involta.actify.ui.screen.viewmodel.AccrueViewModel
import ru.involta.actify.ui.screen.viewmodel.DebitViewModel
import ru.involta.actify.ui.screen.viewmodel.RegistrationViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
  val interactor: Interactor,
  appComponent: AppComponent,
  // Inject your repos here...
) : ViewModel() {

  val innerViewModels: RenderDrawer = RenderDrawer(interactor)

  private val _stateTerminals: MutableStateFlow<Result<TerminalRegistrationResponse>> =
    MutableStateFlow(Result.empty())
  val stateTerminals: StateFlow<Result<TerminalRegistrationResponse>> = _stateTerminals

  var card by mutableStateOf("")
  var isAuth by mutableStateOf(false)
  var currentScreen = mutableStateOf(ActionScreen.NOTHING)

  fun clearCard() = viewModelScope.launch(Dispatchers.IO) {
    card = ""
  }

  fun getTerminal() = viewModelScope.launch(Dispatchers.IO) {
    //if (_stateRegistration.value.status == Result.Status.ERROR) delay(5000)
    _stateTerminals.value = Result.loading()
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

  inner class RenderDrawer(val interactor: Interactor) {

    private var reportViewModel = ReportViewModel(interactor)
    private var optionViewModel = OptionViewModel(interactor)
    private var accrueViewModel = AccrueViewModel(interactor)
    private var debitViewModel = DebitViewModel(interactor)
    private var prizesViewModel = PrizesViewModel(interactor)
    private var registerViewModel = RegistrationViewModel(interactor)
    private var activateViewModel = ActivateTerminalViewModel(interactor)


    @Composable
    fun renderReport(modifier: Modifier = Modifier) {
      val scope = rememberCoroutineScope()
      ReportScreen(reportViewModel.apply { init(scope) }, modifier)
      DisposableEffect(Unit) {
        onDispose {
          reportViewModel = ReportViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderOption() {
      val scope = rememberCoroutineScope()
      OptionScreen(optionViewModel.apply { init(scope) }, card, {
        card = it
      }) {
        currentScreen.value = it
      }
      DisposableEffect(Unit) {
        onDispose {
          optionViewModel = OptionViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderAccrue() {
      val scope = rememberCoroutineScope()
      AccrueScreen(card, accrueViewModel.apply { init(scope) }) {
        currentScreen.value = ActionScreen.NOTHING
        clearCard()
      }
      DisposableEffect(Unit) {
        onDispose {
          accrueViewModel = AccrueViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderDebit() {
      val scope = rememberCoroutineScope()
      DebitScreen(card, debitViewModel.apply { init(scope) }) {
        currentScreen.value = ActionScreen.NOTHING
        clearCard()
      }
      DisposableEffect(Unit) {
        onDispose {
          debitViewModel = DebitViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderPrizes() {
      val scope = rememberCoroutineScope()
      PrizesScreen(card, prizesViewModel.apply { init(scope) }) {
        currentScreen.value = ActionScreen.NOTHING
        clearCard()
      }
      DisposableEffect(Unit) {
        onDispose {
          prizesViewModel = PrizesViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderRegister() {
      val scope = rememberCoroutineScope()
      RegistrationScreen(registerViewModel.apply { init(scope) }, card)
      DisposableEffect(Unit) {
        onDispose {
          registerViewModel = RegistrationViewModel(interactor)
        }
      }
    }

    @Composable
    fun renderActivate(
      modifier: Modifier = Modifier,
      onSuccess: () -> Unit,
    ) {
      val scope = rememberCoroutineScope()
      ActivateTerminalScreen(activateViewModel.apply { init(scope) }, modifier, onSuccess)
      DisposableEffect(Unit) {
        onDispose {
          activateViewModel = ActivateTerminalViewModel(interactor)
        }
      }
    }

  }
}