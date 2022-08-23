package ru.involta.actify.domain.entity.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.involta.actify.domain.entity.api.HasStatusMessage
import ru.involta.actify.domain.entity.api.request.AmountBody

@JsonClass(generateAdapter = true)
data class DebitCheckResponse(
  @Json(name = "success")
  val _success: Boolean = false,
  @Json(name = "status")
  val _status: Int = 0,
  @Json(name = "message")
  override val unsafeMessage: String?,
  @Json(name = "amount")
  val amount: AmountBody
) : HasStatusMessage {
  override val isSuccess = _success || (_status != 0)
  override val message: String = unsafeMessage ?: ""
}