package com.myapp.actify.di

import com.myapp.actify.data.api.ActifyMainApi
import com.myapp.actify.data.api.ActifyRegistrationApi
import com.myapp.data.repo.MyRepo
import com.myapp.ui.feature.main.MainScreenComponent
import com.myapp.ui.feature.main.MainViewModel
import com.myapp.ui.feature.splash.SplashScreenComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class])
interface AppComponent {
    fun inject(splashScreenComponent: SplashScreenComponent)
    fun inject(mainScreenComponent: MainScreenComponent)
    fun inject(mainViewModel: MainViewModel)


    val mainApi: ActifyMainApi
    val registrationApi: ActifyRegistrationApi
    val myRepo: MyRepo
}