package com.myapp.actify.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.myapp.actify.data.api.ActifyMainApi
import com.myapp.actify.data.api.ActifyRegistrationApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {



  @Provides
  @Singleton
  fun httpClient(): OkHttpClient =
    OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()



  @Provides
  @Singleton
  fun provideMainApi(okHttpClient: OkHttpClient): ActifyMainApi {
    val retrofit =  Retrofit.Builder()
      .baseUrl("https://api.ifriend.devolta.ru/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
    return retrofit.create(ActifyMainApi::class.java)
  }

  @Provides
  @Singleton
  fun provideRegistrationApi(okHttpClient: OkHttpClient): ActifyRegistrationApi {
    val retrofit = Retrofit.Builder()
      //todo
      .baseUrl("https://loyalty.actify.ru/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
    return retrofit.create(ActifyRegistrationApi::class.java)
  }
/*
  @Provides
  @Singleton
  fun provideRemoteSource(silverApi: SilverApi): RemoteSourceImpl = RemoteSourceImpl(silverApi)*/

}