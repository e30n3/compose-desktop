package ru.involta.actify.ui.screen.main.nested

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.SendToMobile
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.myapp.ui.element.ActifyButton
import com.myapp.util.Toast
import com.myapp.util.extention.tryToPhoneFormat
import kotlinx.coroutines.delay
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField
import ru.involta.actify.ui.screen.viewmodel.RegistrationViewModel
import ru.involta.actify.ui.theme.defaultFiniteAnimationSpec

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel, phone: String) {
  val def = 16.dp

  val isSend = rememberSaveable() { mutableStateOf(false) }
  val canSend = rememberSaveable() { mutableStateOf(true) }
  val ticks = rememberSaveable() { mutableStateOf(0) }

  val sendSmsState = viewModel.stateSendSms.collectAsState()
  val registrationState = viewModel.stateRegistration.collectAsState()

  LaunchedEffect(Unit) {
    if (viewModel.phone.isEmpty()) {
      viewModel.phone = phone
    }
  }

  LaunchedEffect(ticks.value) {
    if (ticks.value == 0) canSend.value = true
  }

  LaunchedEffect(canSend.value) {
    if (!canSend.value) {
      ticks.value = 120
      while (ticks.value > 0) {
        delay(1000)
        ticks.value--
      }
    }
  }

  LaunchedEffect(key1 = sendSmsState.value.status) {
    when (sendSmsState.value.status) {
      Result.Status.SUCCESS -> {
        isSend.value = true
        canSend.value = false
        viewModel.clearSendSms()
      }
      Result.Status.ERROR -> {
        Toast.makeText( "${sendSmsState.value.exception?.message}")
        viewModel.clearSendSms()
      }
      Result.Status.LOADING -> {}
      Result.Status.EMPTY -> {}
    }
  }

  LaunchedEffect(key1 = registrationState.value.status) {
    when (registrationState.value.status) {
      Result.Status.SUCCESS -> {
        Toast.makeText("Участник успешно зарегистрирован")
        /*navController.navigate(MainRoutes.OPTION.route) {
          popUpTo(MainRoutes.OPTION.route) {
            inclusive = true
          }
        }*/
      }
      Result.Status.ERROR -> {
        Toast.makeText( "${registrationState.value.exception!!.message}")
        //viewModel.clearRegistration()
      }
      Result.Status.LOADING -> {}
      Result.Status.EMPTY -> {}
    }
  }

  Column(
    Modifier.padding(vertical = def),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)/*, modifier = Modifier.animateContentSize()*/
  ) {
    Text(
      text = "Регистрация нового участника",
      Modifier.padding(horizontal = def),
      style = MaterialTheme.typography.h6,
    )
    ActifyTextField(
      modifier = Modifier.padding(horizontal = def),
      value = viewModel.phone,
      onValueChange = {
        viewModel.phone = it
      },
      label = "Номер телефона",
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
      enabled = !isSend.value,
      onDone = {
        viewModel.phone = viewModel.phone.tryToPhoneFormat()
      }
    ) {
      AnimatedVisibility(
        visible = viewModel.phone.isNotBlank() && !isSend.value, modifier = Modifier.padding(def / 8),
        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
      ) {
        IconButton(onClick = {
          viewModel.phone = ""
        }) {
          Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
        }
      }
    }

    AnimatedVisibility(
      visible = isSend.value, modifier = Modifier.padding(horizontal = def),
    ) {
      ActifyTextField(
        value = viewModel.code,
        onValueChange = { new -> viewModel.code = new },
        label = "Код подтверждения",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
      )
    }

    Column() {
      ActifyButton(
        icon = Icons.Filled.SendToMobile,
        enabled = canSend.value && sendSmsState.value.status != Result.Status.LOADING,
        onClick = { viewModel.sendSms() },
        modifier = Modifier.padding(horizontal = def),
        isLoading = sendSmsState.value.status == Result.Status.LOADING
      ) {
        Row(modifier = Modifier.animateContentSize(defaultFiniteAnimationSpec)) {
          Text(text = "Отправить код" + if (isSend.value) " повторно" else "")
          AnimatedVisibility(visible = !canSend.value) {
            Row() {
              Text(text = " (")
              ticks.value.toString().forEach {
                AnimatedContent(targetState = it.toString().toInt(), transitionSpec = {
                  (slideInVertically { height -> -height } + fadeIn() with
                          slideOutVertically { height -> height } + fadeOut())
                    .using(SizeTransform(clip = false))
                }) { digit ->
                  Text(digit.toString())
                }
              }
              Text(text = ")")
            }
          }
        }
      }

      AnimatedVisibility(visible = isSend.value) {
        ActifyButton(
          text = if (viewModel.code.isNotBlank()) "Подтвердить" else "Введите код выше",
          icon = Icons.Filled.GppGood,
          enabled = viewModel.code.isNotBlank() && registrationState.value.status != Result.Status.LOADING,
          modifier = Modifier.padding(horizontal = def),
          isLoading = registrationState.value.status == Result.Status.LOADING
        ) {
          viewModel.registration()
        }
      }
    }
  }
}