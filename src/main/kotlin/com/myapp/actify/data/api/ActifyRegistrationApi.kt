package com.myapp.actify.data.api

import com.myapp.actify.di.RemoteModule
import dagger.Component
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.involta.actify.domain.entity.api.ResponseBody
import javax.inject.Singleton


interface ActifyRegistrationApi {

  @GET("api/send-sms")
  @Headers("Content-Type: application/json")
  suspend fun sendSms(
    //1 - for reg, 0 - for others situations
    @Query("reg") reg: Int,
    @Query("phone") phone: String,
  ): ResponseBody

  @GET("api/reg-by-code")
  @Headers("Content-Type: application/json")
  suspend fun registration(
    @Query("code") code: String,
    @Query("phone") phone: String,
  ): ResponseBody

}