package com.myapp.ui.feature.splash;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SplashScreenComponent_MembersInjector implements MembersInjector<SplashScreenComponent> {
  private final Provider<SplashViewModel> splashViewModelProvider;

  public SplashScreenComponent_MembersInjector(Provider<SplashViewModel> splashViewModelProvider) {
    this.splashViewModelProvider = splashViewModelProvider;
  }

  public static MembersInjector<SplashScreenComponent> create(
      Provider<SplashViewModel> splashViewModelProvider) {
    return new SplashScreenComponent_MembersInjector(splashViewModelProvider);
  }

  @Override
  public void injectMembers(SplashScreenComponent instance) {
    injectSplashViewModel(instance, splashViewModelProvider.get());
  }

  @InjectedFieldSignature("com.myapp.ui.feature.splash.SplashScreenComponent.splashViewModel")
  public static void injectSplashViewModel(SplashScreenComponent instance,
      SplashViewModel splashViewModel) {
    instance.splashViewModel = splashViewModel;
  }
}
