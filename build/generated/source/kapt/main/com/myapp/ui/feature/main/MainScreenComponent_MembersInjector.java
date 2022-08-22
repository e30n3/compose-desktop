package com.myapp.ui.feature.main;

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
public final class MainScreenComponent_MembersInjector implements MembersInjector<MainScreenComponent> {
  private final Provider<MainViewModel> viewModelProvider;

  public MainScreenComponent_MembersInjector(Provider<MainViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<MainScreenComponent> create(
      Provider<MainViewModel> viewModelProvider) {
    return new MainScreenComponent_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(MainScreenComponent instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("com.myapp.ui.feature.main.MainScreenComponent.viewModel")
  public static void injectViewModel(MainScreenComponent instance, MainViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
