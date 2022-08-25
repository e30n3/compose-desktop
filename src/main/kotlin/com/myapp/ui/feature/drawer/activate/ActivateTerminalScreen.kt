package com.myapp.ui.feature.drawer.activate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.myapp.ui.element.ActifyButton
import com.myapp.util.Toast
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField

@Composable
fun ActivateTerminalScreen(
  viewModel: ActivateTerminalViewModel,
  modifier: Modifier = Modifier,
  onSuccess: () -> Unit,
) {

  val registrationState = viewModel.stateRegistration.collectAsState()
  val def = 16.dp

  LaunchedEffect(key1 = registrationState.value.status) {
    when (registrationState.value.status) {
      Result.Status.SUCCESS -> {
        Toast.makeText("Успешно активирован\n${registrationState.value.data?.name} ${registrationState.value.data?.id}")
        viewModel.clearViewModel()
        onSuccess.invoke()
      }
      Result.Status.ERROR -> {
        Toast.makeText( "${registrationState.value.exception!!.message}")
        viewModel.clearRegistration()
      }
      Result.Status.LOADING -> {}
      Result.Status.EMPTY -> {}
    }
  }



  Column(modifier, verticalArrangement = Arrangement.spacedBy(def)) {
    Text(text = "Активировать терминал", style = MaterialTheme.typography.h6)
    ActifyTextField(
      value = viewModel.id,
      onValueChange = { viewModel.id = it },
      label = "Номер терминала",
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
    )
    ActifyTextField(
      value = viewModel.key,
      onValueChange = { viewModel.key = it },
      label = "Пароль терминала",
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
    )
    ActifyButton(
      "Подтвердить",
      icon = Icons.Filled.GppGood,
      enabled = registrationState.value.status != Result.Status.LOADING,
      isLoading = registrationState.value.status == Result.Status.LOADING
    ) {
      println("Click")
      viewModel.registration()
    }
  }
}