package com.myapp.ui.feature.main

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.myapp.actify.data.Interactor
import com.myapp.actify.di.AppComponent
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse
import com.myapp.ui.feature.action.PrizesViewModel
import com.myapp.ui.feature.drawer.activate.ActivateTerminalScreen
import com.myapp.ui.feature.drawer.activate.ActivateTerminalViewModel
import com.myapp.ui.feature.drawer.report.ReportScreen
import com.myapp.ui.feature.drawer.report.ReportViewModel
import com.myapp.ui.feature.option.OptionViewModel
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
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

    @Composable
    fun renderReport(modifier: Modifier = Modifier) {
      val scope = rememberCoroutineScope()
      ReportScreen(ReportViewModel(interactor).apply { init(scope) }, modifier)
    }

    @Composable
    fun renderOption() {
      val scope = rememberCoroutineScope()
      OptionScreen(OptionViewModel(interactor).apply { init(scope) },{
        card = it
      }) {
        currentScreen.value = it
      }
    }

    @Composable
    fun renderAccrue() {
      val scope = rememberCoroutineScope()
      AccrueScreen(card, AccrueViewModel(interactor).apply { init(scope) })
    }

    @Composable
    fun renderDebit() {
      val scope = rememberCoroutineScope()
      DebitScreen(card, DebitViewModel(interactor).apply { init(scope) })
    }

    @Composable
    fun renderPrizes() {
      val scope = rememberCoroutineScope()
      PrizesScreen(card, PrizesViewModel(interactor).apply { init(scope) })
    }

    @Composable
    fun renderRegister() {
      val scope = rememberCoroutineScope()
      RegistrationScreen(RegistrationViewModel(interactor).apply { init(scope) }, card)
    }

    @Composable
    fun renderActivate(
      modifier: Modifier = Modifier,
      onSuccess: () -> Unit,
    ) {
      val scope = rememberCoroutineScope()
      ActivateTerminalScreen(ActivateTerminalViewModel(interactor).apply { init(scope) }, modifier, onSuccess)
    }

  }
}