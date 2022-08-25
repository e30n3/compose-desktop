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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.ui.element.DatePicker
import com.myapp.util.extention.getCurrentDate
import com.myapp.util.extention.prettyString
import ru.involta.actify.domain.Result
import ru.involta.actify.domain.entity.api.response.ReportBody
import ru.involta.actify.util.extention.shimmer

@Composable
fun ReportScreen(viewModel: ReportViewModel, modifier: Modifier = Modifier) {
  val def = remember { 16.dp }
  val reportState = viewModel.stateReport.collectAsState()
  /*  val dialogState = rememberMaterialDialogState()


    val selectedDateLowApi = rememberSaveable { mutableStateOf( getCurrentDate() ) }
    val dialogLowApi = DatePickerDialog(
      LocalContext.current,
      0,
      { _, year, month, dayOfMonth ->
        selectedDateLowApi.value = "$year-${month + 1}-$dayOfMonth"
        viewModel.report(selectedDateLowApi.value)
      },
      selectedDateLowApi.value.split('-').component1().toInt(),
      selectedDateLowApi.value.split('-').component2().toInt() - 1,
      selectedDateLowApi.value.split('-').component3().toInt(),
    )

    if (Constant.isApiForLocalDate) {
      val selectedData = remember { mutableStateOf(LocalDate.now()) }
      MaterialDialog(
        dialogState = dialogState,
        buttons = {
          positiveButton("Ok")
          negativeButton("Cancel")
        },
        shape = remember { AbsoluteSmoothCornerShape(def * 2, 100) }
      ) {
        datepicker(
          selectedData.value,
          "Выберите дату",
          yearRange = IntRange(2000, LocalDate.now().year),
        ) { date ->
          selectedData.value = date
          viewModel.report(selectedData.value.asApiFormat)
          // Do stuff with java.time.LocalDate object which is passed in
        }
      }
    }*/


  Column() {
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

    Crossfade(targetState = reportState.value.status) {
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
          Text(text = reportState.value.exception?.message ?: "")
        }

        Result.Status.LOADING -> {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .shimmer(true)
          )
        }

        Result.Status.EMPTY -> {
          viewModel.report()
        }
      }
    }
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
            text = "Начисление № ${r.id}",
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