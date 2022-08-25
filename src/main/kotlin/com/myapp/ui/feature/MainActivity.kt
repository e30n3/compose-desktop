package com.myapp.ui.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.myapp.App
import com.myapp.ui.navigation.NavHostComponent
import com.myapp.util.Toast
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.involta.actify.ui.theme.ActifyTheme
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
  companion object {
    fun getStartIntent(): Intent {
      return Intent(MainActivity::class).apply {
        // data goes here
      }
    }
  }



  override fun onCreate() {
    super.onCreate()
    val width = 1024.dp
    val height = 600.dp


    setContent {
      val mainWindowState = rememberWindowState(
        width = width,
        height = height,
        placement = WindowPlacement.Floating,
        position = WindowPosition(Alignment.Center),
      )

      Toast.init()
      Window(
        onCloseRequest = ::exitApplication,
        title = "${App.appArgs.appName} (${App.appArgs.version})",
        icon = painterResource("drawables/launcher_icons/system.png"),
        state = mainWindowState,
      ) {
        ActifyTheme() {
          // Igniting navigation
          rememberRootComponent(factory = ::NavHostComponent).render()
        }
      }
    }
  }
}