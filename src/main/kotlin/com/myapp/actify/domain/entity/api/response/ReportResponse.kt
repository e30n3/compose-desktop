package ru.involta.actify.domain.entity.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ReportResponse(
  val isSuccess: Boolean,
  val data: List<ReportBody> = emptyList(),
  val message: String = "Проверьте корректность введенных даных"
)

@JsonClass(generateAdapter = true)
data class ReportBody(
  @Json(name = "id")
  val id: Long,
  @Json(name = "guid")
  val guid: String,
  @Json(name = "type_id")
  val typeId: Long,
  @Json(name = "date")
  val date: String,
  @Json(name = "card")
  val card: String,
  @Json(name = "is_cancelled")
  val iCancelled: Boolean,
  @Json(name = "is_active")
  val isActive: Boolean,
  @Json(name = "totalBonus")
  val totalBonus: TotalBonusBody,
  @Json(name = "totalAmount")
  val totalAmount: TotalAmountBody,
  @Json(name = "operations")
  val operations: List<OperationBody>,
)

@JsonClass(generateAdapter = true)
data class TotalBonusBody(
  @Json(name = "1")
  val value: Double,
)

@JsonClass(generateAdapter = true)
data class TotalAmountBody(
  @Json(name = "1")
  val value: Double,
)

@JsonClass(generateAdapter = true)
data class OperationBody(
  @Json(name = "bonus_type")
  val bonusType: String,
  @Json(name = "category_id")
  val categoryId: Long,
  @Json(name = "bonus")
  val bonus: Double,
  @Json(name = "is_active")
  val isActive: Boolean,
  @Json(name = "action_id")
  val actionId: Long?,
  @Json(name = "action")
  val action: String?,
) {
  val bonusTypeToString: String
    get() =
      when (bonusType) {
        "BASIC" -> "Универсальные"
        "COMPANY" -> "Фирменные"
        else -> bonusType
      }

}


@JsonClass(generateAdapter = true)
data class ReportElement(
  @Json(name = "prize")
  val prize: String,
  @Json(name = "for")
  val description: String,
  @Json(name = "prizeId")
  val prizeId: Long
)