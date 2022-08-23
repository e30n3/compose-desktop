package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BalanceRequest(
  @Json(name = "track")
  val phoneOrCard: String,

  @Json(name = "terminal")
  val terminalId: Long,
)