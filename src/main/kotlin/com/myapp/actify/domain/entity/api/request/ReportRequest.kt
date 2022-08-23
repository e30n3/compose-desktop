package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReportRequest(
  @Json(name = "terminal")
  val terminalId: Long,

  @Json(name = "date")
  val date: String,
)