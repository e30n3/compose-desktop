package com.myapp.ui.feature.splash;

import com.myapp.data.repo.MyRepo;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<MyRepo> myRepoProvider;

  public SplashViewModel_Factory(Provider<MyRepo> myRepoProvider) {
    this.myRepoProvider = myRepoProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(myRepoProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<MyRepo> myRepoProvider) {
    return new SplashViewModel_Factory(myRepoProvider);
  }

  public static SplashViewModel newInstance(MyRepo myRepo) {
    return new SplashViewModel(myRepo);
  }
}
