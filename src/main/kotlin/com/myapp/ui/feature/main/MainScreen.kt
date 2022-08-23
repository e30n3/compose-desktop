package com.myapp.ui.feature.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.ui.value.R
import ru.involta.actify.domain.Result


@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val welcomeText by viewModel.welcomeText.collectAsState()
    val getBalanceState = viewModel.stateBalance.collectAsState()
    /*val data by viewModel.*/

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = welcomeText,
                style = MaterialTheme.typography.h3
            )
            Crossfade(getBalanceState.value.status) {
                when (it) {
                    Result.Status.SUCCESS -> getBalanceState.value.data?.let { data ->
                        Text("$data")
                    }
                    Result.Status.ERROR -> Text("Ошибка")
                    Result.Status.LOADING -> Text("Загрузка")
                    Result.Status.EMPTY -> Text("Пусто")
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    viewModel.onClickMeClicked()
                }
            ) {
                Text(text = R.string.ACTION_MAIN_CLICK_ME)
            }
        }
    }
}