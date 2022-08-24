package com.myapp.ui.feature.drawer.terminal

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
import com.myapp.ui.feature.main.MainViewModel
import ru.involta.actify.domain.Result
import ru.involta.actify.util.extention.glow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TerminalStatusScreen(
  modifier: Modifier = Modifier,
  viewModel: MainViewModel,
  onExit: () -> Unit
): () -> Unit {
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
      Crossfade(targetState = viewModel.stateTerminals.value.status) {
        when (viewModel.stateTerminals.value.status) {
          Result.Status.SUCCESS -> {
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
                Text(text = "${viewModel.stateTerminals.value.data?.name} - ${viewModel.stateTerminals.value.data?.id}")
              }, singleLineSecondaryText = false
            )
          }
          else -> {
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
  return { viewModel.getTerminal() }
}