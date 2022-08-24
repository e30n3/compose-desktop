package com.myapp.ui.feature.main;

import com.myapp.actify.data.Interactor;
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
  private final Provider<Interactor> interactorProvider;

  public MainViewModel_Factory(Provider<Interactor> interactorProvider) {
    this.interactorProvider = interactorProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(interactorProvider.get());
  }

  public static MainViewModel_Factory create(Provider<Interactor> interactorProvider) {
    return new MainViewModel_Factory(interactorProvider);
  }

  public static MainViewModel newInstance(Interactor interactor) {
    return new MainViewModel(interactor);
  }
}
