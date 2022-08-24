package com.myapp.actify.domain.entity.api.response

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

  fun pack() = "$id~$name".toByteArray(Charsets.UTF_16)

  companion object {
    fun unpack(bytes: ByteArray) = bytes.toString(Charsets.UTF_16).split("~").run {
      TerminalRegistrationResponse(id = component1().toLong(), name = component2())
    }
  }

}
