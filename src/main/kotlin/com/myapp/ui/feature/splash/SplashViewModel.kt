package com.myapp.ui.feature.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.myapp.data.repo.MyRepo
import com.myapp.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val SPLASH_DELAY = 1200L // 2 seconds of splash delay
        const val VISIBLE_DELAY = 300L
    }

    var isVisible by mutableStateOf(false)

    private val _isSplashFinished = MutableStateFlow(false)
    val isSplashFinished: StateFlow<Boolean> = _isSplashFinished

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        viewModelScope.launch {
            delay(VISIBLE_DELAY)
            isVisible = true
            delay(SPLASH_DELAY)
            _isSplashFinished.value = true
        }
    }
}