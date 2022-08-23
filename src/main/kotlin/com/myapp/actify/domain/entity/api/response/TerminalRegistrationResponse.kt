package ru.involta.actify.domain.entity.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.involta.actify.domain.entity.api.HasStatusMessage

@JsonClass(generateAdapter = true)
data class TerminalRegistrationResponse(
  @Json(name = "status")
  val _status: Int = 0,

  @Json(name = "message")
  override val unsafeMessage: String = "",

  @Json(name = "aes_key")
  val key: String = "",

  @Json(name = "name")
  val name: String = "",

  @Json(name = "id")
  val id: Long = 0,

  ) : HasStatusMessage {
  override val isSuccess = _status != 0
  override val message = unsafeMessage
}
