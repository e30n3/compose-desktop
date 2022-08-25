package com.myapp.ui.feature.splash;

import com.myapp.data.repo.MyRepo;
import com.myapp.util.ViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR+\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/myapp/ui/feature/splash/SplashViewModel;", "Lcom/myapp/util/ViewModel;", "()V", "_isSplashFinished", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isSplashFinished", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "<set-?>", "isVisible", "()Z", "setVisible", "(Z)V", "isVisible$delegate", "Landroidx/compose/runtime/MutableState;", "init", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "Companion", "compose-desktop-template"})
public final class SplashViewModel extends com.myapp.util.ViewModel {
    @org.jetbrains.annotations.NotNull
    public static final com.myapp.ui.feature.splash.SplashViewModel.Companion Companion = null;
    public static final long SPLASH_DELAY = 1200L;
    public static final long VISIBLE_DELAY = 300L;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isVisible$delegate = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSplashFinished = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSplashFinished = null;
    
    @javax.inject.Inject
    public SplashViewModel() {
        super();
    }
    
    public final boolean isVisible() {
        return false;
    }
    
    public final void setVisible(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSplashFinished() {
        return null;
    }
    
    @java.lang.Override
    public void init(@org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope viewModelScope) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/myapp/ui/feature/splash/SplashViewModel$Companion;", "", "()V", "SPLASH_DELAY", "", "VISIBLE_DELAY", "compose-desktop-template"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}