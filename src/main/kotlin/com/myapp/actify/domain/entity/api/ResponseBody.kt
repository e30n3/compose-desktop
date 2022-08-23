package ru.involta.actify.domain.entity.api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseBody(
  @Json(name = "success")
  val _success: Boolean = false,
  @Json(name = "status")
  val _status: Int = 0,
  @Json(name = "message")
  override val unsafeMessage: String?,
) : HasStatusMessage {
  override val isSuccess = _success || (_status != 0)
  override val message: String = unsafeMessage ?: ""
}

@JsonClass(generateAdapter = true)
data class MessageBody(
  @Json(name = "message")
  val value: String?,
)