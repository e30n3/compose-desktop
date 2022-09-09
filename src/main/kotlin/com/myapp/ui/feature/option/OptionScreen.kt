package com.myapp.ui.feature.option

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.myapp.ui.element.ActifyButton
import com.myapp.ui.element.BalanceCard
import com.myapp.ui.feature.main.ActionScreen
import com.myapp.util.extention.tryToPhoneFormat
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField

@Composable
fun OptionScreen(
  viewModel: OptionViewModel,
  card: String,
  onCardChange: (String) -> Unit,
  selectedScreen: (ActionScreen) -> Unit
) {
  val def = 16.dp
  val stateBalance = viewModel.stateBalance.collectAsState()
  val haptic = LocalHapticFeedback.current

  LaunchedEffect(card) {
    if (card == "") viewModel.clearBalance()
    selectedScreen(ActionScreen.NOTHING)
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
    /*.animateContentSize(defaultFiniteAnimationSpec)*/
  ) {
    Spacer(modifier = Modifier.height(def).weight(1f))
    BalanceCard(
      response = stateBalance.value,
      Modifier
        .fillMaxWidth()
        .padding(start = def, end = def)
    )
    Spacer(modifier = Modifier.height(def).weight(1f))
    val focusManager = LocalFocusManager.current
    ActifyTextField(
      value = card,
      onValueChange = {
        val card = it.filter { c -> c in "+1234567890" }.runCatching {
          substring(0, it.length.coerceAtMost(12))
        }.getOrNull() ?: ""
        if (it.isBlank()) viewModel.clearBalance()
        if (it.length >= 12) {
          focusManager.clearFocus(true)
          viewModel.balance(card)
        }
        onCardChange(card)
        selectedScreen(ActionScreen.NOTHING)
      }, label = "Телефон либо скан карты",
      modifier = Modifier.padding(horizontal = def),
      keyboardOptions = KeyboardOptions(
        keyboardType =
        KeyboardType.Number
      ),
      onStart = {
        viewModel.balance(card)
        var localCard = card
        if (localCard.length >= 12) localCard = ""
        onCardChange(localCard)
        selectedScreen(ActionScreen.NOTHING)
      },
      onDone = {
        var localCard = card
        localCard = card.tryToPhoneFormat()
        viewModel.balance(localCard)
        onCardChange(localCard)
        selectedScreen(ActionScreen.NOTHING)
      },
      onFocus = {
        selectedScreen(ActionScreen.NOTHING)
      },
      visualTransformation = if (card.length >= 12 && '+' !in card) PasswordVisualTransformation() else VisualTransformation.None
    ) {
      Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        AnimatedVisibility(
          visible = card.isNotBlank(), modifier = Modifier.padding(def / 8),
          enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
          exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
        ) {
          IconButton(onClick = {
            focusManager.clearFocus(true)
            viewModel.balance(card)
            selectedScreen(ActionScreen.NOTHING)
          }) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "")
          }
        }
        AnimatedVisibility(
          visible = card.isNotBlank(), modifier = Modifier.padding(def / 8),
          enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
          exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
        ) {
          IconButton(onClick = {
            focusManager.clearFocus(true)
            onCardChange("")
            viewModel.clearBalance()
            selectedScreen(ActionScreen.NOTHING)
          }) {
            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
          }
        }
      }
      /*AnimatedVisibility(
        viewModel.cardIsFound && viewModel.card.isNotBlank(),
        modifier = Modifier.padding(def / 8),
        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
      ) {
        IconButton(onClick = {
          when (type) {
            OptionScreenType.BALANCE -> viewModel.balance(true)
            OptionScreenType.ACCRUAL -> navController.navigate("${BottomRoutes.ACCRUE.route}/${viewModel.card}")
            OptionScreenType.PRIZES -> navController.navigate("${BottomRoutes.PRIZES.route}/${viewModel.card}")
            OptionScreenType.DEBIT -> navController.navigate("${BottomRoutes.DEBIT.route}/${viewModel.card}")
          }
        }) {
          Icon(Icons.Filled.NavigateNext, contentDescription = "")
        }
      }*/
    }

    Spacer(modifier = Modifier.height(def))
    ActifyButton(
      text = "Начисление",
      Icons.Filled.Input,
      modifier = Modifier.padding(horizontal = def),
      enabled = stateBalance.value.status == Result.Status.SUCCESS
    ) {
      selectedScreen(ActionScreen.ACCRUE)
    }
    ActifyButton(
      text = "Списание",
      Icons.Filled.AllOut,
      modifier = Modifier.padding(horizontal = def),
      enabled = stateBalance.value.status == Result.Status.SUCCESS
    ) {
      selectedScreen(ActionScreen.DEBIT)
    }
    ActifyButton(
      text = "Доступные призы",
      Icons.Filled.WorkspacePremium,
      modifier = Modifier.padding(horizontal = def),
      enabled = stateBalance.value.status == Result.Status.SUCCESS
    ) {
      selectedScreen(ActionScreen.PRIZES)
    }
    ActifyButton(
      text = "Зарегистрировать",
      Icons.Filled.PersonAdd,
      modifier = Modifier.padding(start = def, end = def, bottom = def),
      enabled = stateBalance.value.status == Result.Status.ERROR && stateBalance.value.exception?.message?.contains("не найден") ?: false
    ) {
      selectedScreen(ActionScreen.REGISTRATION)
    }
    Spacer(modifier = Modifier.height(def).weight(1f))
  }

}
