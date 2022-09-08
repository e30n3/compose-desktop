package com.myapp.ui.feature.action


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Input
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myapp.ui.element.ActifyButton
import com.myapp.ui.element.ActifyDialog
import com.myapp.ui.element.BalanceCard
import com.myapp.util.Toast
import com.myapp.util.extention.prettyString
import com.myapp.util.extention.rubleWord
import com.myapp.util.extention.tryToSafeCardFormat
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField
import ru.involta.actify.ui.screen.viewmodel.AccrueViewModel
import ru.involta.actify.ui.theme.defaultFiniteAnimationSpec

@Composable
fun AccrueScreen(phoneOrCard: String, viewModel: AccrueViewModel, onFinish:()->Unit) {
  val def = 16.dp
  val accrueState = viewModel.stateAccrue.collectAsState()
  val isOpen = rememberSaveable { mutableStateOf(false) }


  ActifyDialog(isOpen = isOpen.value, onClose = {
    isOpen.value = false
    if (accrueState.value.status == Result.Status.SUCCESS && accrueState.value.data?._status == 1) {
      onFinish()
    }
  }) {
    Spacer(modifier = Modifier.height(def))
    Text(
      text = "Успешная покупка",
      style = MaterialTheme.typography.h6,
      modifier = Modifier
        .padding(horizontal = def)
        .fillMaxWidth(),
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(def / 2))
    Text(
      text = "Сумма ${viewModel.amount.toDoubleOrNull()?.prettyString} ${viewModel.amount.toDoubleOrNull()?.rubleWord}",
      modifier = Modifier
        .padding(horizontal = def)
        .fillMaxWidth(),
      textAlign = TextAlign.Center
    )
    BalanceCard(
      response = accrueState.value,
      Modifier
        .fillMaxWidth()
        .padding(def),
      true
    )
  }



  LaunchedEffect(key1 = accrueState.value.status) {
    when (accrueState.value.status) {
      Result.Status.SUCCESS -> {
        isOpen.value = true
        /*Toast.makeText(context, "Успешно зарегистрирован", Toast.LENGTH_SHORT).show()
        navController.navigate(BottomRoutes.NOTHING.route) {
          popUpToTop(navController)
        }*/
      }

      Result.Status.ERROR -> {
        Toast.makeText("${accrueState.value.exception!!.message}")
        //viewModel.clearRegistration()
      }

      Result.Status.LOADING -> {}
      Result.Status.EMPTY -> {}
    }
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
  ) {
    Spacer(modifier = Modifier.height(def).weight(1f))
    Text(
      text = "Начисление",
      style = MaterialTheme.typography.h6,
      modifier = Modifier.padding(start = def, end = def, top = def)
    )
    Spacer(modifier = Modifier.height(def / 2))
    Text(text = "Карта: ${phoneOrCard.tryToSafeCardFormat()}", modifier = Modifier.padding(horizontal = def))
    Spacer(modifier = Modifier.height(def).weight(1f))
    ActifyTextField(
      value = viewModel.amount,
      onValueChange = {
        viewModel.amount = it.filter { c -> c in "1234567890." }.runCatching {
          substring(0, it.length.coerceAtMost(7))
        }.getOrNull() ?: ""
      },
      label = "Сумма покупки",
      modifier = Modifier.padding(horizontal = def),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      onDone = {
        if (viewModel.amount.toDoubleOrNull() == null) viewModel.amount = "0"
      }
    )
    Spacer(modifier = Modifier.height(def))
    ActifyButton(
      "Начислить бонусы",
      Icons.Filled.Input,
      modifier = Modifier.padding(start = def, end = def, bottom = def),
      enabled = viewModel.amount.isNotEmpty() && accrueState.value.status != Result.Status.LOADING,
      isLoading = accrueState.value.status == Result.Status.LOADING
    ) {
      viewModel.accrue(phoneOrCard)
    }
    Spacer(modifier = Modifier.height(def).weight(1f))
  }
}