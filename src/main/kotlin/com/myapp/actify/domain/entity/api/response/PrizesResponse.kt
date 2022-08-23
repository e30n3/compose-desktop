package ru.involta.actify.domain.entity.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PrizesResponse(
  val isSuccess: Boolean,
  val data: List<PrizeBody> = emptyList(),
  val message: String = "Проверьте корректность введенных даных"
)

@JsonClass(generateAdapter = true)
data class PrizeBody(
  @Json(name = "id")
  val id: Long,
  @Json(name = "name")
  val name: String,
  @Json(name = "data")
  val data: List<PrizeElement>
)


@JsonClass(generateAdapter = true)
data class PrizeElement(
  @Json(name = "prize")
  val prize: String,
  @Json(name = "for")
  val description: String,
  @Json(name = "prizeId")
  val prizeId: Long
)