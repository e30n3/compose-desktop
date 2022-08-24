package com.myapp.util.extention

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun String.tryToPhoneFormat(): String {
  if (contains('+')) return this
  return when (length) {
    11 -> "+7" + drop(1)
    10 -> "+7$this"
    else -> this
  }
}

fun String.tryToSafeCardFormat(): String = if (contains('+')) this else "••••••••••••"


fun getCurrentDate(): String =
  LocalDate.now().asApiFormat

val isAdequate: Boolean = Date().asApiFormat.split('-').run {
  (component1().toIntOrNull() ?: 2022) > 2022 /*|| (component2().toIntOrNull() ?: 12) > 12*/
}


val LocalDate.asApiFormat: String
  get() = this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

val Date.asApiFormat: String
  get() = SimpleDateFormat("yyyy-MM-dd").format(this)

