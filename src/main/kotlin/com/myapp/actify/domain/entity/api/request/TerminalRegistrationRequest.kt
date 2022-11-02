package ru.involta.actify.domain.entity.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TerminalRegistrationRequest(
  @Json(name = "id")
  val id: Long,

  @Json(name = "key")
  val key: String,
)
