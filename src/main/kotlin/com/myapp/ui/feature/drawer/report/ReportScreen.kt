package com.myapp.ui.feature.drawer.report

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.ui.element.DatePicker
import com.myapp.util.ShimmerBrush
import com.myapp.util.extention.getCurrentDate
import com.myapp.util.extention.prettyString
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.OperationBody
import ru.involta.actify.domain.entity.api.response.ReportBody
import ru.involta.actify.domain.entity.api.response.TotalAmountBody
import ru.involta.actify.domain.entity.api.response.TotalBonusBody
import ru.involta.actify.util.extention.shimmer

@Composable
fun ReportScreen(viewModel: ReportViewModel, modifier: Modifier = Modifier) {
  val def = remember { 16.dp }
  val reportState = viewModel.stateReport.collectAsState()

  LaunchedEffect(reportState.value.status) {
    if (reportState.value.status == Result.Status.EMPTY) viewModel.report()
  }

  Column(Modifier.fillMaxSize()) {
    Surface(color = MaterialTheme.colors.secondary) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .padding(horizontal = def, vertical = def / 4)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = "Отчёт", style = MaterialTheme.typography.h6)
        Row() {
          IconButton(onClick = { viewModel.report() }) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = "")
          }
          val selectedDate = rememberSaveable { mutableStateOf(getCurrentDate()) }
          val isOpen = rememberSaveable { mutableStateOf(false) }
          DatePicker(isOpen.value, { isOpen.value = false }, selectedDate.value) {
            selectedDate.value = it
            viewModel.report(selectedDate.value)
          }
          IconButton(onClick = { isOpen.value = true }) {
            Icon(imageVector = Icons.Filled.EditCalendar, contentDescription = "")
          }
        }
      }
    }

    Crossfade(targetState = reportState.value.status, modifier = Modifier.fillMaxSize()) {
      when (it) {
        Result.Status.SUCCESS -> reportState.value.data?.let { data ->
          if (data.isEmpty())
            Text(text = "В этот день не было транзакций", Modifier.padding(def))
          else
            LazyColumn(modifier) {
              items(data.reversed()) { element ->
                ReportItem(r = element)
              }
            }
        }

        Result.Status.ERROR -> {
          Text(text = reportState.value.exception?.message ?: "", Modifier.padding(def))
        }

        Result.Status.LOADING -> {

          LazyColumn(modifier) {
            items(100) {
              ReportItemShimmer()
            }
          }

          /* Text(
             "4234",
             modifier = Modifier
               .shimmer()
               .fillMaxWidth()
               .height(500.dp)
               .shimmer(),
             textAlign = TextAlign.Center
           )*/
        }

        Result.Status.EMPTY -> {

        }
      }
    }
  }
}

@Composable
fun ReportItemShimmer() {
  val r = ReportBody(
    0,
    "",
    0,
    "",
    "",
    false,
    false,
    TotalBonusBody(0.0),
    TotalAmountBody(0.0),
    listOf(OperationBody("", 0, 0.0, false, null, null), OperationBody("", 0, 0.0, false, null, null))
  )
  Column {
    Column(
      Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .shimmer()
    ) {
      Column() {
        Text(
          text = "",
          style = MaterialTheme.typography.h5,
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center
        )
        Text(
          text = "",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
          fontSize = 14.sp,
          fontWeight = FontWeight.Light
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = "")
        Text(text = "")
      }
      Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = "")
        Text(text = "")
      }
      Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = "")
        Text(text = "")
      }
      Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = "")
        Text(text = "")
      }
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = "", style = MaterialTheme.typography.h5)
      r.operations.forEach {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = "")
          Text(text = "")
        }
      }
    }
    Divider()
  }

}

@Composable
fun ReportItem(r: ReportBody) {
  SelectionContainer {
    Column {
      Column(
        Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Column() {
          Text(
            text = "${
              when (r.typeId) {
                1L -> "Списание"
                2L -> "Начисление"
                else -> "Выдача приза"
              }
            } № ${r.id}",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
          )
          Text(
            text = r.guid,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = "Дата")
          Text(text = r.date)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = "Номер карты")
          Text(text = r.card)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = "Сумма")
          Text(text = r.totalAmount.value.prettyString)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
          Text(text = "Бонусы")
          Text(text = r.totalBonus.value.prettyString)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Детали бонусов", style = MaterialTheme.typography.h5)
        r.operations.forEach {
          Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = it.bonusTypeToString)
            Text(text = it.bonus.prettyString)
          }
        }
      }
      Divider()
    }
  }
}