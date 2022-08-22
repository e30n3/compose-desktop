package com.myapp.ui.feature.main;

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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<MyRepo> myRepoProvider;

  public MainViewModel_Factory(Provider<MyRepo> myRepoProvider) {
    this.myRepoProvider = myRepoProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(myRepoProvider.get());
  }

  public static MainViewModel_Factory create(Provider<MyRepo> myRepoProvider) {
    return new MainViewModel_Factory(myRepoProvider);
  }

  public static MainViewModel newInstance(MyRepo myRepo) {
    return new MainViewModel(myRepo);
  }
}
