package com.myapp.ui.element

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myapp.util.extention.prettyString
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.BalanceResponse
import ru.involta.actify.util.extention.shimmer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BalanceCard(response: Result<BalanceResponse>, modifier: Modifier = Modifier, forAccrual: Boolean = false) {
  val def = remember { 16.dp }
  val cardShape = remember { RoundedCornerShape(def) }
  val elevation =
    animateDpAsState(targetValue = if (response.status == Result.Status.SUCCESS) 8.dp else 2.dp, tween(700))
  Card(
    modifier,
    shape = cardShape,
    backgroundColor = MaterialTheme.colors.surface,
    elevation = elevation.value
  ) {
    Column(
      Modifier
        .shimmer(response.status == Result.Status.LOADING)
        .padding(def)
    ) {
      AnimatedContent(targetState = response.status) {
        Column(Modifier.height(remember(forAccrual) { 48.dp * 3 })) {
          when (it) {
            Result.Status.SUCCESS -> response.data?.let { data ->
              Column {
                /*BalanceRow(left = "Баланс карты", right = data.basic.prettyString, forAccrual)*/
                if (!forAccrual) BalanceRow(left = "Всего бонусов", right = data.total.prettyString)
                else BalanceRow(
                  left = "Всего бонусов",
                  right = (data.basic + data.company).prettyString,
                  forAccrual
                )
                BalanceRow(left = "Универсальных бонусов", right = data.basic.prettyString, forAccrual)
                BalanceRow(left = "Фирменных бонусов", right = data.company.prettyString, forAccrual)
              }
            }
            Result.Status.ERROR -> {
              Box(Modifier.fillMaxSize()) {
                Text(
                  text = response.exception?.message ?: "",
                  Modifier.align(Alignment.Center),
                  style = MaterialTheme.typography.h6
                )
              }
            }
            Result.Status.LOADING -> {
            }
            Result.Status.EMPTY -> {
              Box(Modifier.fillMaxSize()) {
                Text(
                  text = "Начните вводить данные",
                  Modifier.align(Alignment.Center),
                  style = MaterialTheme.typography.h6
                )
              }
            }/*FSf6EVjOsh_qEzzGnYKNyhYslw4s3PZK*/
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalanceRow(left: String, right: String, forAccrual: Boolean = false) {
  ListItem(text = {
    Text(text = left)
  }, trailing = {
    Text(
      text = if (forAccrual) "+$right" else right,
      style = MaterialTheme.typography.h6,
      color = if (forAccrual) Color(0xff66bb6a) else Color.Unspecified
    )
  })
}