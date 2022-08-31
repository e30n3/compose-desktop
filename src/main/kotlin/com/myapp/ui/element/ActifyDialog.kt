package com.myapp.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import androidx.compose.ui.zIndex
import com.myapp.util.Toast
import kotlinx.coroutines.delay
import ru.involta.actify.ui.theme.ActifyTheme

@Composable
fun ActifyDialog(isOpen: Boolean, onClose: () -> Unit = {}, content: @Composable ColumnScope.() -> Unit) {
  val dialogState = rememberDialogState(size = DpSize(500.dp, 400.dp))
  Dialog(
    state = dialogState,
    visible = isOpen,
    onCloseRequest = onClose,
    transparent = false,
    undecorated = false,
    resizable = false,
    title = "Actify"
  ) {
    ActifyTheme {
      WindowDraggableArea {
        /*  Box {
            Surface(
              shape = CircleShape,
              color = MaterialTheme.colors.surface,
              modifier = Modifier.zIndex(2f).align(Alignment.TopEnd).padding(16.dp)
            ) {
              IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, "")
              }
            }*/
        /*Box(Modifier.padding(32.dp)) {*/
        Surface(
          color = MaterialTheme.colors.surface,
          shape = RoundedCornerShape(0.dp),
          elevation = 16.dp,
          modifier = Modifier.fillMaxSize()
        ) {
          Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            content(this)
          }
        }
        /*  }*/
        /*   }*/
      }
    }
  }
}