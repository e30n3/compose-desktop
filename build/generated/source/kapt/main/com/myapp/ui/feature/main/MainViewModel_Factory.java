package com.myapp.ui.feature.main;

import com.myapp.actify.data.Interactor;
import com.myapp.actify.di.AppComponent;
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

  private final Provider<AppComponent> appComponentProvider;

  public MainViewModel_Factory(Provider<Interactor> interactorProvider,
      Provider<AppComponent> appComponentProvider) {
    this.interactorProvider = interactorProvider;
    this.appComponentProvider = appComponentProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(interactorProvider.get(), appComponentProvider.get());
  }

  public static MainViewModel_Factory create(Provider<Interactor> interactorProvider,
      Provider<AppComponent> appComponentProvider) {
    return new MainViewModel_Factory(interactorProvider, appComponentProvider);
  }

  public static MainViewModel newInstance(Interactor interactor, AppComponent appComponent) {
    return new MainViewModel(interactor, appComponent);
  }
}
