package com.myapp.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.involta.actify.ui.element.ActifyTextField

@Composable
fun DatePicker(isOpen: Boolean, onClose: () -> Unit, selectedDate: String, result: (String) -> Unit) {

  val day = selectedDate.split("-").component3()
  val month = selectedDate.split("-").component2()
  val year = selectedDate.split("-").component1()

  ActifyDialog(isOpen, onClose) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
      Text("Укажите дату", style = MaterialTheme.typography.h6)
      ActifyTextField(value = day, onValueChange = {
        result(
          "$year-$month-${
            it.runCatching {
              filter { c -> c in "1234567890" }.toInt().coerceIn(0, 31).toString()
            }.getOrNull() ?: ""
          }"
        )
      }, modifier = Modifier.wrapContentWidth(), label = "День")
      ActifyTextField(value = month, onValueChange = {
        result(
          "$year-${
            it.runCatching {
              filter { c -> c in "1234567890" }.toInt().coerceIn(0, 12).toString()
            }.getOrNull() ?: ""
          }-$day"
        )
      }, modifier = Modifier.wrapContentWidth(), label = "Месяц")
      ActifyTextField(value = year, onValueChange = {
        result(
          "${
            it.runCatching {
              filter { c -> c in "1234567890" }.toInt().coerceIn(0, 9999).toString()
            }.getOrNull() ?: ""
          }-$month-$day"
        )
      }, modifier = Modifier.wrapContentWidth(), label = "Год")
    }
  }
}