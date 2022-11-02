package com.myapp.actify.data.api

import com.myapp.actify.domain.entity.api.response.BalanceResponse
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.involta.actify.domain.entity.api.ResponseBody
import ru.involta.actify.domain.entity.api.request.*
import ru.involta.actify.domain.entity.api.response.*


interface ActifyMainApi {

  @POST("v1/terminal/card/balance")
  @Headers("Content-Type: application/json")
  suspend fun balance(
    @Body request: BalanceRequest
  ): BalanceResponse

  @POST("v1/terminal/register")
  @Headers("Content-Type: application/json")
  suspend fun terminalRegistration(
    @Body request: TerminalRegistrationRequest
  ): TerminalRegistrationResponse

  @POST("v1/terminal/card/prizes")
  @Headers("Content-Type: application/json")
  suspend fun prizes(
    @Body request: BalanceRequest
  ): List<PrizeBody>

  @POST("v1/terminal/card/prizes")
  @Headers("Content-Type: application/json")
  suspend fun prizesAnotherBody(
    @Body request: BalanceRequest
  ): ResponseBody

  @POST("v1/terminal/withdraws/create")
  @Headers("Content-Type: application/json")
  suspend fun debit(
    @Body request: DebitRequest
  ): ResponseBody


  @POST("v1/terminal/card/grant")
  @Headers("Content-Type: application/json")
  suspend fun claimPrize(
    @Body request: PrizeRequest
  ): ResponseBody


  @POST("v1/terminal/withdraws/check")
  @Headers("Content-Type: application/json")
  suspend fun debitCheck(
    @Body request: DebitCheckRequest
  ): DebitCheckResponse


  @POST("v1/terminal/report/z-report")
  @Headers("Content-Type: application/json")
  suspend fun report(
    @Body request: ReportRequest
  ): List<ReportBody>

  @POST("v1/terminal/report/z-report")
  @Headers("Content-Type: application/json")
  suspend fun reportAnotherBody(
    @Body request: ReportRequest
  ): ResponseBody

  @POST("v1/terminal/payments/create")
  @Headers("Content-Type: application/json")
  suspend fun accrue(
    @Body request: AccrueRequest
  ): BalanceResponse

}