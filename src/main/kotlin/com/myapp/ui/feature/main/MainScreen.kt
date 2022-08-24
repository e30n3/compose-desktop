package com.myapp.ui.feature.main

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.myapp.ui.value.R


@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
  val def = 16.dp
  val scaffoldState = rememberScaffoldState()

  Row {
    //drawer
    Surface(
      elevation = def,
      modifier = Modifier.zIndex(2f).weight(1f).fillMaxHeight(),
      color = MaterialTheme.colors.surface
    ) {

    }
    //content
    Box(
      modifier = Modifier.zIndex(1f).weight(1f).fillMaxHeight(),
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
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
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
