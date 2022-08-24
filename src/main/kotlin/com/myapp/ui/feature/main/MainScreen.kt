package com.myapp.ui.feature.main

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
          Text(text = "Дополнительно", style = MaterialTheme.typography.h6)
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
                coroutineScope.launch { scaffoldState.drawerState.close() }
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
    Box(
      modifier = Modifier.zIndex(1f).weight(2f).fillMaxHeight(),
      contentAlignment = Alignment.Center
    ) {
      Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
          TopAppBar(elevation = def) {
            Icon(
              painterResource(R.drawable.actifyLogo),
              contentDescription = "",
              Modifier.padding(vertical = def / 1.1f, horizontal = def)
            )
          }
        },
        bottomBar = {
          BottomAppBar(cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
              Row() {
                AnimatedVisibility(
                  visible = true/*terminalState.value.status == Result.Status.SUCCESS && backStackEntry.value?.destination?.route != MainRoutes.OPTION.route*/,
                  enter = slideInVertically { it / 2 } + fadeIn(),
                  exit = slideOutVertically { it / 2 } + fadeOut()
                ) {
                  IconButton(
                    onClick = { /*mainNavController.popBackStack()*/ },
                  ) {
                    Icon(Icons.Filled.ArrowBack, "")
                  }
                }
                AnimatedVisibility(
                  visible = true/*terminalState.value.status == Result.Status.SUCCESS && backStackEntry.value?.destination?.route != MainRoutes.OPTION.route*/,
                  enter = slideInVertically { it / 2 } + fadeIn(),
                  exit = slideOutVertically { it / 2 } + fadeOut()
                ) {
                  IconButton(onClick = {
                    /*mainNavController.navigate(MainRoutes.OPTION.route) {
                      popUpTo(MainRoutes.OPTION.route) {
                        inclusive = true
                      }
                    }*/
                  }
                  ) {
                    Icon(Icons.Filled.Home, "")
                  }
                }
              }
            }
          }
        },
      ) {

      }
    }
  }


}
