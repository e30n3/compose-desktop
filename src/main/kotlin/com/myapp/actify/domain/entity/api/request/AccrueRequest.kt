package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccrueRequest(
  @Json(name = "track")
  val phoneOrCard: String,

  @Json(name = "terminal")
  val terminalId: Long,

  /**Два знака после точки*/
  @Json(name = "amount")
  val amount: AmountBody,

  )

@JsonClass(generateAdapter = true)
data class AmountBody(
  @Json(name = "1")
  val value: Double,
)

