package com.myapp.util

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberDialogState
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import ru.involta.actify.ui.theme.ActifyTheme


object Toast {
  private var isOpen by mutableStateOf(false)
  private var text by mutableStateOf("")
  private var closeDelay by mutableStateOf(1f)

  @Composable
  fun init() {
    val dialogState = rememberDialogState()
    Dialog(
      state = dialogState,
      visible = isOpen,
      onCloseRequest = {
        isOpen = false
      },
      transparent = true,
      undecorated = true,
      resizable = false
    ) {
      /*LaunchedEffect(key1=isOpen) {
        if (isOpen) {
          closeDelay = 1f
          while (closeDelay > 0f) {
            closeDelay -= 0.01f
            delay(20)
          }
          isOpen = false
        }
      }*/

      ActifyTheme() {
        WindowDraggableArea {
          Box(Modifier.padding(32.dp)) {
            Surface(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(16.dp), elevation = 16.dp) {
              /*LinearProgressIndicator(closeDelay, Modifier.fillMaxWidth().zIndex(1f).align(Alignment.BottomCenter))*/
              Column(
                Modifier.padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Row(
                  horizontalArrangement = Arrangement.SpaceBetween,
                  modifier = Modifier.fillMaxWidth(),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("Actify Сообщение", style = MaterialTheme.typography.h6)
                  IconButton(onClick = {
                    isOpen = false
                  }) {
                    Icon(Icons.Filled.Close, "")
                  }
                }
                Spacer(Modifier.height(8.dp))
                Text(text)
              }
            }
          }
        }
      }
    }
  }

  fun makeText(text: String) {
    closeDelay = 1f
    isOpen = false
    this.text = text
    isOpen = true
  }
}