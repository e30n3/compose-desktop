package com.myapp.actify.domain.entity.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.involta.actify.domain.entity.api.HasStatusMessage

@JsonClass(generateAdapter = true)
data class BalanceResponse(

    @Json(name = "status")
    val _status: Int = 0,

    @Json(name = "message")
    override val unsafeMessage: String?,

    @Json(name = "error")
    val unsafeError: String?,

    @Json(name = "BASIC")
    val basic: Double = 0.0,

    @Json(name = "COMPANY")
    val company: Double = 0.0,

    @Json(name = "ACTION")
    val action: Double = 0.0,

    @Json(name = "CROSS")
    val cross: Double = 0.0,

    @Json(name = "TOTAL")
    val total: Double = basic + company,

    ) : HasStatusMessage {
    override val isSuccess = _status != 0
    override val message = unsafeMessage ?: unsafeError ?: ""
}