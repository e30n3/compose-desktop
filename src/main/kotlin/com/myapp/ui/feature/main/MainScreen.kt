package com.myapp.ui.feature.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.myapp.ui.feature.drawer.terminal.TerminalStatusScreen
import com.myapp.ui.value.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.involta.actify.domain.Result


enum class ActionScreen() {
  ACCRUE,
  DEBIT,
  PRIZES,
  REGISTRATION,
  NOTHING
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
  viewModel: MainViewModel,
) {
  val def = 16.dp
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val terminalState = viewModel.stateTerminals.collectAsState()



  LaunchedEffect(key1 = terminalState.value.status) {
    when (terminalState.value.status) {
      Result.Status.SUCCESS -> {
      }

      Result.Status.ERROR -> {
      }

      Result.Status.LOADING -> {
      }

      Result.Status.EMPTY -> {
        viewModel.getTerminal()
      }
    }
  }

  Row {
    //drawer
    Surface(
      elevation = def,
      modifier = Modifier.zIndex(2f).weight(1f).fillMaxHeight(),
      color = MaterialTheme.colors.surface
    ) {
      Column(Modifier.fillMaxSize()) {
        TopAppBar(contentPadding = PaddingValues(horizontal = def)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(text = "Дополнительно", style = MaterialTheme.typography.h6)
          }
        }
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
          lateinit var update: () -> Unit
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            update = TerminalStatusScreen(Modifier.padding(def), viewModel) {
              coroutineScope.launch {
                delay(100)
                viewModel.getTerminal()
              }
            }
            AnimatedContent(viewModel.isAuth) {
              if (it) Column(
                Modifier
                  .wrapContentHeight()
                  .weight(1f)
              ) {
                viewModel.innerViewModels.renderReport()
              }
              else Spacer(Modifier.weight(1f))
            }
          }
          AnimatedVisibility(!viewModel.isAuth) {
            Box(
              modifier = Modifier.padding(def),
            ) {
              viewModel.innerViewModels.renderActivate(Modifier.align(Alignment.BottomCenter)) {
                update.invoke()
                viewModel.getTerminal()
                /*mainNavController.navigate(MainRoutes.OPTION.route) {
                  popUpToTop(mainNavController)
                }*/
              }
            }
          }
        }
      }
    }
    //content

    Scaffold(
      modifier = Modifier.zIndex(1f).weight(1f).fillMaxHeight(),
      scaffoldState = scaffoldState,
      topBar = {
        TopAppBar(elevation = def) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(
              painterResource(R.drawable.actifyLogo),
              contentDescription = "",
              Modifier.padding(vertical = def / 1.1f, horizontal = def)
            )
          }
        }
      },
    ) {
      Surface(modifier = Modifier.fillMaxSize(), elevation = 8.dp) {
        AnimatedVisibility(viewModel.isAuth) {
          viewModel.innerViewModels.renderOption()
        }
      }
    }
    //content2
    Surface(
      elevation = def,
      modifier = Modifier.zIndex(2f).weight(1f).fillMaxHeight(),
      color = MaterialTheme.colors.surface
    ) {
      Column(Modifier.fillMaxSize()) {
        TopAppBar(contentPadding = PaddingValues(horizontal = def)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(text = "Действие", style = MaterialTheme.typography.h6)
          }
        }
        Crossfade(viewModel.currentScreen.value) {
          when (it) {
            ActionScreen.ACCRUE -> {
              viewModel.innerViewModels.renderAccrue()
            }

            ActionScreen.DEBIT -> {
              viewModel.innerViewModels.renderDebit()
            }

            ActionScreen.PRIZES -> {
              viewModel.innerViewModels.renderPrizes()
            }

            ActionScreen.REGISTRATION -> {
              viewModel.innerViewModels.renderRegister()
            }

            ActionScreen.NOTHING -> {

            }
          }
        }
      }
    }
  }
}
