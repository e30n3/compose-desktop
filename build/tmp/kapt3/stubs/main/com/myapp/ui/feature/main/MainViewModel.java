package com.myapp.ui.feature.main;

import com.myapp.actify.data.Interactor;
import com.myapp.data.repo.MyRepo;
import com.myapp.util.ViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.flow.StateFlow;
import ru.involta.actify.domain.Result;
import ru.involta.actify.domain.entity.api.response.BalanceResponse;
import ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020 J\u0006\u0010$\u001a\u00020\"R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001a\u00a8\u0006&"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel;", "Lcom/myapp/util/ViewModel;", "myRepo", "Lcom/myapp/data/repo/MyRepo;", "interactor", "Lcom/myapp/actify/data/Interactor;", "(Lcom/myapp/data/repo/MyRepo;Lcom/myapp/actify/data/Interactor;)V", "_stateBalance", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/involta/actify/domain/Result;", "Lru/involta/actify/domain/entity/api/response/BalanceResponse;", "_stateTerminals", "Lru/involta/actify/domain/entity/api/response/TerminalRegistrationResponse;", "_welcomeText", "", "<set-?>", "", "isAuth", "()Z", "setAuth", "(Z)V", "isAuth$delegate", "Landroidx/compose/runtime/MutableState;", "stateBalance", "Lkotlinx/coroutines/flow/StateFlow;", "getStateBalance", "()Lkotlinx/coroutines/flow/StateFlow;", "stateTerminals", "getStateTerminals", "welcomeText", "getWelcomeText", "balance", "Lkotlinx/coroutines/Job;", "clearBalance", "", "getTerminal", "onClickMeClicked", "Companion", "compose-desktop-template"})
public final class MainViewModel extends com.myapp.util.ViewModel {
    private final com.myapp.data.repo.MyRepo myRepo = null;
    private final com.myapp.actify.data.Interactor interactor = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> _stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isAuth$delegate = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.BalanceResponse>> _stateBalance = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.BalanceResponse>> stateBalance = null;
    @org.jetbrains.annotations.NotNull
    public static final com.myapp.ui.feature.main.MainViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String INIT_WELCOME_MSG = "Hello World!";
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _welcomeText = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> welcomeText = null;
    
    @javax.inject.Inject
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.myapp.data.repo.MyRepo myRepo, @org.jetbrains.annotations.NotNull
    com.myapp.actify.data.Interactor interactor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> getStateTerminals() {
        return null;
    }
    
    public final boolean isAuth() {
        return false;
    }
    
    public final void setAuth(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getTerminal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.BalanceResponse>> getStateBalance() {
        return null;
    }
    
    public final void clearBalance() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job balance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getWelcomeText() {
        return null;
    }
    
    public final void onClickMeClicked() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel$Companion;", "", "()V", "INIT_WELCOME_MSG", "", "compose-desktop-template"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}