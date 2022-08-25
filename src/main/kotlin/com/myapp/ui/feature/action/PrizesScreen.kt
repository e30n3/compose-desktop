package ru.involta.actify.ui.screen.main.nested

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.myapp.ui.element.ActifyButton
import com.myapp.ui.element.ActifyDialog
import com.myapp.util.Toast
import com.myapp.util.extention.tryToSafeCardFormat
import ru.involta.actify.domain.Result
import ru.involta.actify.ui.element.ActifyTextField
import com.myapp.ui.feature.action.PrizesViewModel
import ru.involta.actify.ui.theme.defaultFiniteAnimationSpec
import ru.involta.actify.util.extention.shimmer

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun PrizesScreen(cardOrPhone: String, viewModel: PrizesViewModel, onFinish:()->Unit) {
  val def = 16.dp
  val prizesState = viewModel.statePrizes.collectAsState()
  val claimPrizeState = viewModel.stateClaimPrize.collectAsState()

  val isOpen = rememberSaveable { mutableStateOf(false) }



  LaunchedEffect(key1 = claimPrizeState.value.status) {
    if (claimPrizeState.value.status == Result.Status.SUCCESS && claimPrizeState.value.data?._status == 1) {
      isOpen.value = true
    }
    if (claimPrizeState.value.status == Result.Status.ERROR && claimPrizeState.value.exception?.message?.isNotBlank() == true) {
      Toast.makeText(claimPrizeState.value.exception?.message ?: "")
      viewModel.code = ""
      viewModel.clearClaimPrize()
    }
  }

  LaunchedEffect(key1 = viewModel.codeVisibility) {
    if (viewModel.codeVisibility) {
      isOpen.value = true
      viewModel.codeVisibility = false
    }
  }


  ActifyDialog(isOpen = isOpen.value, onClose = {
    isOpen.value = false
    if (claimPrizeState.value.status == Result.Status.SUCCESS && claimPrizeState.value.data?._status == 1) {
      onFinish()
    }
    /*navController.navigate(MainRoutes.OPTION.route) {
      popUpTo(MainRoutes.OPTION.route) {
        inclusive = true
      }
    }*/
  }) {
    AnimatedContent(targetState = claimPrizeState.value.data?._status == 1) {
      if (it) Column(
        Modifier
          .fillMaxWidth()
          .animateContentSize(defaultFiniteAnimationSpec)
          .padding(def), horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Spacer(modifier = Modifier.height(def))
        Text(text = "Успешно выдан", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(def / 2))
        Text(text = "приз ${viewModel.selectedPrize?.prize}")
        Spacer(modifier = Modifier.height(def))
      }
      else
        Column(
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
            enabled = (viewModel.code.isNotBlank() && claimPrizeState.value.status != Result.Status.LOADING),
            modifier = Modifier.padding(horizontal = def),
            isLoading = claimPrizeState.value.status == Result.Status.LOADING
          ) {
            viewModel.claimPrize(cardOrPhone)
          }
        }
    }
  }


  Crossfade(targetState = prizesState.value.status) {
    when (it) {
      Result.Status.SUCCESS -> prizesState.value.data?.let { data ->
        if (data.isNotEmpty())
          LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.animateContentSize(defaultFiniteAnimationSpec),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            item() {
              Column() {
                Text(
                  text = "Доступные призы",
                  style = MaterialTheme.typography.h6,
                  modifier = Modifier
                    .padding(horizontal = def)
                    .fillMaxWidth(),
                  textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(def / 2))
                Text(
                  text = "Карта: ${cardOrPhone.tryToSafeCardFormat()}",
                  modifier = Modifier
                    .padding(horizontal = def)
                    .fillMaxWidth(),
                  textAlign = TextAlign.Center
                )
              }
            }
            item() { Spacer(Modifier.height(def)) }
            items(data) { element ->
              Text(
                text = "АКЦИЯ \'${element.name}\'",
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                  .background(MaterialTheme.colors.secondary)
                  .padding(def)
                  .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle2
              )
              element.data.forEach { subElement ->
                val isSelect = remember(
                  key1 = viewModel.selectedPrize,
                  key2 = viewModel.selectedActionId
                ) { viewModel.selectedPrize == subElement && viewModel.selectedActionId == element.id }
                Row(
                  verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .then(
                      if (isSelect)
                        Modifier
                          .zIndex(2f)
                      else Modifier
                    )
                    .scale(animateFloatAsState(targetValue = if (isSelect) 1.2f else 1f).value)
                ) {
                  AnimatedVisibility(
                    visible = isSelect,
                    modifier = Modifier.padding(start = animateDpAsState(targetValue = if (isSelect) def * 2 else 0.dp).value)
                  ) {
                    AnimatedContent(
                      targetState = claimPrizeState.value.status == Result.Status.LOADING &&
                          subElement.prizeId == viewModel.selectedPrize?.prizeId &&
                          element.id == viewModel.selectedActionId
                    ) { state ->
                      if (state)
                        CircularProgressIndicator(Modifier.padding(horizontal = def / 2))
                      else
                        IconButton(onClick = { viewModel.claimPrize(cardOrPhone) }) {
                          Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(horizontal = def)
                          )
                        }
                    }
                  }
                  val cornerRadius = animateDpAsState(targetValue = if (isSelect) def else 0.dp)
                  Surface(
                    elevation = animateDpAsState(targetValue = if (isSelect) def else 0.dp).value,
                    shape = RoundedCornerShape(cornerRadius.value, 0.dp, 0.dp, cornerRadius.value)
                  ) {
                    ListItem(
                      secondaryText = { Text(text = subElement.description) },
                      modifier = Modifier.then(
                        if (isSelect) Modifier else Modifier.clickable(claimPrizeState.value.status != Result.Status.LOADING) {
                          viewModel.selectedActionId = element.id
                          viewModel.selectedPrize = subElement
                          viewModel.clearClaimPrize()
                          viewModel.code = ""
                        }
                      )
                    ) { Text(text = subElement.prize) }
                  }
                }
                Divider(Modifier)
              }
            }
          }
        else {
          Box(Modifier.fillMaxSize()) {
            Text(text = "Доступных призов нет", Modifier.align(Alignment.Center))
          }
        }
      }

      Result.Status.ERROR -> {
        Box(Modifier.fillMaxSize()) {
          Text(text = "${prizesState.value.exception?.message}", Modifier.align(Alignment.Center))
        }
      }

      Result.Status.LOADING -> {
        Column {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .shimmer()

          )
        }
      }

      Result.Status.EMPTY -> {
        Column {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .shimmer()
          )
        }
        viewModel.prizes(cardOrPhone)
      }
    }
  }
}
