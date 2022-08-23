package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DebitRequest(
  @Json(name = "track")
  val phoneOrCard: String,

  @Json(name = "terminal")
  val terminalId: Long,

  @Json(name = "amount")
  val amount: AmountBody,

  @Json(name = "withdraw")
  val debit: DebitBody,

  @Json(name = "confirm_code")
  val confirmCode: String = "",
)


@JsonClass(generateAdapter = true)
data class DebitBody(
  @Json(name = "1")
  val value: Double,
)