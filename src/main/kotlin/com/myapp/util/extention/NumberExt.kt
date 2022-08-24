package com.myapp.util.extention

import kotlin.math.roundToLong

val Number.prettyString: String
  get() = toDouble().times(100.0).roundToLong().div(100.0).toString()
    .run { dropLast(if (contains(".0") && last() == '0') 2 else 0) }

val Number.bonusWord: String
  get() = prettyString.run {
    when {
      contains('.') -> "бонуса"
      length > 1 && substring(length - 2, length) in listOf("11", "12", "13", "14") -> "бонусов"
      last() in "1" -> "бонус"
      last() in "234" -> "бонуса"
      else -> "бонусов"
    }
  }

val Number.rubleWord: String
  get() = prettyString.run {
    when {
      contains('.') -> "рубля"
      length > 1 && substring(length - 2, length) in listOf("11", "12", "13", "14") -> "рублей"
      last() in "1" -> "рубль"
      last() in "234" -> "рубля"
      else -> "рублей"
    }
  }