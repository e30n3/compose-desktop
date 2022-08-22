package com.myapp.ui.feature.splash;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/myapp/ui/feature/splash/SplashViewModel;", "Lcom/myapp/util/ViewModel;", "myRepo", "Lcom/myapp/data/repo/MyRepo;", "(Lcom/myapp/data/repo/MyRepo;)V", "_isSplashFinished", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isSplashFinished", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "init", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "Companion", "compose-desktop-template"})
public final class SplashViewModel extends com.myapp.util.ViewModel {
    private final com.myapp.data.repo.MyRepo myRepo = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.myapp.ui.feature.splash.SplashViewModel.Companion Companion = null;
    public static final long SPLASH_DELAY = 2000L;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSplashFinished = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSplashFinished = null;
    
    @javax.inject.Inject()
    public SplashViewModel(@org.jetbrains.annotations.NotNull()
    com.myapp.data.repo.MyRepo myRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSplashFinished() {
        return null;
    }
    
    @java.lang.Override()
    public void init(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope viewModelScope) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/myapp/ui/feature/splash/SplashViewModel$Companion;", "", "()V", "SPLASH_DELAY", "", "compose-desktop-template"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}