package ru.involta.actify.ui.screen.main.nested

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.Input
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.myapp.ui.element.ActifyButton
import com.myapp.ui.element.ActifyDialog
import com.myapp.util.Toast
import com.myapp.util.extention.bonusWord
import com.myapp.util.extention.isAdequate
import com.myapp.util.extention.prettyString
import com.myapp.util.extention.tryToSafeCardFormat
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField
import ru.involta.actify.ui.screen.viewmodel.DebitViewModel
import ru.involta.actify.ui.theme.defaultFiniteAnimationSpec
import ru.involta.actify.ui.theme.shimmerBgColor
import ru.involta.actify.util.extention.shimmer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DebitScreen(phoneOrCard: String, viewModel: DebitViewModel, onFinish: () -> Unit) {
  val def = 16.dp
  val scrollState = rememberScrollState()
  val checkState = viewModel.stateCheck.collectAsState()
  val debitState = viewModel.stateDebit.collectAsState()
  val isOpen = rememberSaveable { mutableStateOf(false) }

  LaunchedEffect(key1 = debitState.value.status) {
    if (debitState.value.status == Result.Status.SUCCESS && debitState.value.data?._status == 1) {
      isOpen.value = true
    }
    if (debitState.value.status == Result.Status.ERROR && debitState.value.exception?.message?.isNotBlank() == true) {
      Toast.makeText(debitState.value.exception?.message ?: "")
      viewModel.code = ""
      viewModel.clearDebit()
    }
  }

  LaunchedEffect(key1 = viewModel.codeVisibility) {
    if (viewModel.codeVisibility) {
      isOpen.value = true
      viewModel.codeVisibility = false
    }
  }

  LaunchedEffect(key1 = checkState.value.status) {
    if (checkState.value.status == Result.Status.EMPTY)
      viewModel.debitCheck(phoneOrCard)
  }


  ActifyDialog(isOpen = isOpen.value, onClose = {
    isOpen.value = false
    if (debitState.value.status == Result.Status.SUCCESS && debitState.value.data?._status == 1) {
      onFinish()
    }
    /*isOpen.value = false
    if (debitState.value.status == Result.Status.SUCCESS && debitState.value.data?._status == 1){}*/
    /*navController.navigate(MainRoutes.OPTION.route) {
      popUpTo(MainRoutes.OPTION.route) {
        inclusive = true
      }
    }*/
  }) {
    AnimatedContent(targetState = debitState.value.data?._status == 1) {
      if (it) Column(
        Modifier
          .fillMaxWidth()
          .animateContentSize(defaultFiniteAnimationSpec)
          .padding(def), horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Spacer(modifier = Modifier.height(def))
        Text(text = "Успешное списание", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(def / 2))
        Text(text = "Списано ${viewModel.debit} ${viewModel.debit.toDouble().bonusWord}")
        Spacer(modifier = Modifier.height(def))
      }
      else Column(
        Modifier
          .fillMaxWidth()
          .animateContentSize(defaultFiniteAnimationSpec)
          .padding(bottom = def),
        verticalArrangement = Arrangement.spacedBy(def)
      ) {
        Text(
          text = "Введите код",
          style = MaterialTheme.typography.h6,
          modifier = Modifier
            .padding(start = def, end = def, top = def)
            .fillMaxWidth(),
          textAlign = TextAlign.Center
        )
        ActifyTextField(
          value = viewModel.code,
          onValueChange = {
            viewModel.code = it.filter { c -> c in "1234567890" }
          },
          label = "Код подтверждения",
          modifier = Modifier.padding(horizontal = def),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        ActifyButton(
          text = if (viewModel.code.isNotBlank()) "Подтвердить" else "Введите код выше",
          icon = Icons.Filled.GppGood,
          enabled = (viewModel.code.isNotBlank() && debitState.value.status != Result.Status.LOADING),
          modifier = Modifier.padding(horizontal = def),
          isLoading = debitState.value.status == Result.Status.LOADING
        ) {
          viewModel.debit(phoneOrCard)
        }
      }
    }
  }


  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .animateContentSize(defaultFiniteAnimationSpec)
      .verticalScroll(scrollState)
  ) {
    Text(
      text = "Списание",
      style = MaterialTheme.typography.h6,
      modifier = Modifier.padding(start = def, end = def, top = def)
    )
    Spacer(modifier = Modifier.height(def / 2))
    Text(text = "Карта: ${phoneOrCard.tryToSafeCardFormat()}", modifier = Modifier.padding(horizontal = def))
    Spacer(modifier = Modifier.height(def / 2))
    Row(Modifier.animateContentSize(defaultFiniteAnimationSpec)) {
      Text(text = "Доступно ")
      Text(
        text = checkState.value.data?.amount?.value?.prettyString ?: viewModel.lastCheckValue,
        Modifier.shimmer(checkState.value.status != Result.Status.SUCCESS)
      )
      Text(
        text = " " + (checkState.value.data?.amount?.value?.bonusWord
          ?: (viewModel.lastCheckValue.toDoubleOrNull() ?: 0.0).bonusWord)
      )
    }
    Spacer(modifier = Modifier.height(def))
    ActifyTextField(
      value = viewModel.amount,
      onValueChange = {
        viewModel.amount =
          it.filter { c -> c in "1234567890." }.runCatching {
            substring(0, it.length.coerceAtMost(7))
          }.getOrNull() ?: ""
      },
      label = "Сумма покупки",
      modifier = Modifier.padding(horizontal = def),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      onDone = {
        if (viewModel.amount.toDoubleOrNull() == null) viewModel.amount = "0"
      },
    )
    Spacer(modifier = Modifier.height(def))
    ActifyTextField(
      value = viewModel.debit,
      onValueChange = {
        viewModel.debit = it.filter { c -> c in "1234567890." }.runCatching {
          substring(0, it.length.coerceAtMost(7))
        }.getOrNull() ?: ""
      },
      label = "Можно списать",
      modifier = Modifier.padding(horizontal = def),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      onDone = {
        if (viewModel.debit.toDoubleOrNull() == null) viewModel.debit = "0"
        viewModel.debit = viewModel.debit.toDouble().coerceAtMost(
          viewModel.stateCheck.value.data?.amount?.value?.coerceAtMost(
            viewModel.amount.toDoubleOrNull() ?: Double.MAX_VALUE
          ) ?: Double.MAX_VALUE
        ).toInt().toString()
      },
      enabled = checkState.value.status == Result.Status.SUCCESS && viewModel.amount.isNotBlank()
    ) {
      AnimatedVisibility(
        visible = viewModel.debit != (viewModel.amount.toDoubleOrNull()
          ?: 0.0).coerceAtMost(checkState.value.data?.amount?.value ?: Double.MAX_VALUE).toInt()
          .toString() && checkState.value.status == Result.Status.SUCCESS && viewModel.amount.isNotBlank(),
        modifier = Modifier.padding(def / 8),
        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
      ) {
        IconButton(
          onClick = {
            viewModel.debit =
              "${
                viewModel.stateCheck.value.data?.amount?.value?.coerceAtMost(viewModel.amount.toDoubleOrNull() ?: Double.MAX_VALUE)
                  ?.toInt() ?: ""
              }"
          },
        ) {
          //Icon(imageVector = Icons.Filled.Add, contentDescription = "")
          Text(
            "MAX",
            fontWeight = FontWeight.Bold,
            color = if (isAdequate) Color.Unspecified else MaterialTheme.colors.primary
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(def))
    ActifyButton(
      "Списать бонусы",
      Icons.Filled.Input,
      modifier = Modifier.padding(start = def, end = def, bottom = def),
      enabled = viewModel.amount.isNotBlank() &&
          checkState.value.status != Result.Status.LOADING &&
          viewModel.debit.isNotBlank() &&
          debitState.value.status != Result.Status.LOADING,
      isLoading = debitState.value.status == Result.Status.LOADING
    ) {
      viewModel.debit(phoneOrCard)
    }
  }
}

