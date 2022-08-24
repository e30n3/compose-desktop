package com.myapp.ui.feature.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.myapp.actify.data.Interactor
import com.myapp.data.repo.MyRepo
import com.myapp.util.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
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
    // Inject your repos here...
) : ViewModel() {

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

    private val _stateBalance: MutableStateFlow<Result<BalanceResponse>> =
        MutableStateFlow(Result.empty())
    val stateBalance: StateFlow<Result<BalanceResponse>> = _stateBalance
    fun clearBalance() {
        _stateBalance.value = Result.empty()
    }

    fun balance() =
        viewModelScope.launch(Dispatchers.IO) {
            //if (_stateSendSms.value.status == Result.Status.ERROR) delay(5000)
            _stateBalance.value = Result.loading()
            //delay(2000)
            interactor.getBalance("883700040920")
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