package com.myapp.ui.feature.drawer

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.involta.actify.domain.Result
import ru.involta.actify.util.extention.glow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TerminalStatusScreen(
  modifier: Modifier = Modifier,
  viewModel: TerminalStatusViewModel,
  onExit: () -> Unit
): () -> Unit {
  val terminalsState = viewModel.stateTerminals.collectAsState()
  val def = 16.dp
  LaunchedEffect(key1 = Unit) {
    viewModel.getTerminal()
  }
  Column(modifier) {
    Surface(
      elevation = def / 2,
      shape = RoundedCornerShape(def),
      color = MaterialTheme.colors.surface,
      modifier = Modifier.glow(MaterialTheme.colors.primary)
    ) {
      Crossfade(targetState = terminalsState.value.status) {
        when (it) {
          Result.Status.SUCCESS -> terminalsState.value.data?.let { terminal ->
            ListItem(modifier = Modifier
              //.padding(horizontal = def)
              .fillMaxWidth(),
              secondaryText = {
                Text(text = "Текущий терминал")
              },
              trailing = {
                IconButton(onClick = {
                  viewModel.logOut()
                  onExit.invoke()
                }) {
                  Icon(imageVector = Icons.Filled.Logout, contentDescription = "")
                }
              }, text = {
                Text(text = "${terminal.name} - ${terminal.id}")
              }, singleLineSecondaryText = false
            )
          }
          else -> terminalsState.value.exception?.let { e ->
            Surface(
              elevation = def / 2,
              shape = RoundedCornerShape(def),
              modifier = Modifier.glow(MaterialTheme.colors.primary),
              color = MaterialTheme.colors.surface
            ) {
              ListItem(modifier = Modifier
                //.padding(horizontal = def)
                .fillMaxWidth(),
                secondaryText = {
                  Text(text = "Активируйте терминал, чтобы пользоваться приложением")
                }, text = {
                  Text(text = "Терминал не активирован")
                }, singleLineSecondaryText = false
              )
            }
          }
        }
      }
    }
  }
  return { viewModel.getTerminal() }
}