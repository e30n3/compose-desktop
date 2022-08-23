package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrizeRequest(
  @Json(name = "track")
  val phoneOrCard: String,

  @Json(name = "terminal")
  val terminalId: Long,

  @Json(name = "action_id")
  val actionId: Long,

  @Json(name = "prize_id")
  val prizeId: Long,

  @Json(name = "confirm_code")
  val confirmCode: String,
)