package com.myapp.di;

import com.myapp.data.repo.MyRepo;
import com.myapp.ui.feature.main.MainScreenComponent;
import com.myapp.ui.feature.main.MainScreenComponent_MembersInjector;
import com.myapp.ui.feature.main.MainViewModel;
import com.myapp.ui.feature.splash.SplashScreenComponent;
import com.myapp.ui.feature.splash.SplashScreenComponent_MembersInjector;
import com.myapp.ui.feature.splash.SplashViewModel;
import dagger.internal.DaggerGenerated;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    public AppComponent build() {
      return new AppComponentImpl();
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AppComponentImpl appComponentImpl = this;

    private AppComponentImpl() {


    }

    private SplashViewModel splashViewModel() {
      return new SplashViewModel(new MyRepo());
    }

    private MainViewModel mainViewModel() {
      return new MainViewModel(new MyRepo());
    }

    @Override
    public void inject(SplashScreenComponent splashScreenComponent) {
      injectSplashScreenComponent(splashScreenComponent);
    }

    @Override
    public void inject(MainScreenComponent mainScreenComponent) {
      injectMainScreenComponent(mainScreenComponent);
    }

    private SplashScreenComponent injectSplashScreenComponent(SplashScreenComponent instance) {
      SplashScreenComponent_MembersInjector.injectSplashViewModel(instance, splashViewModel());
      return instance;
    }

    private MainScreenComponent injectMainScreenComponent(MainScreenComponent instance) {
      MainScreenComponent_MembersInjector.injectViewModel(instance, mainViewModel());
      return instance;
    }
  }
}
