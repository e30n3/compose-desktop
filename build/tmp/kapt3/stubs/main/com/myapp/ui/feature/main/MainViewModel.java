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

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001aR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016\u00a8\u0006\u001e"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel;", "Lcom/myapp/util/ViewModel;", "interactor", "Lcom/myapp/actify/data/Interactor;", "(Lcom/myapp/actify/data/Interactor;)V", "_stateBalance", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/involta/actify/domain/Result;", "Lru/involta/actify/domain/entity/api/response/BalanceResponse;", "_stateTerminals", "Lru/involta/actify/domain/entity/api/response/TerminalRegistrationResponse;", "<set-?>", "", "isAuth", "()Z", "setAuth", "(Z)V", "isAuth$delegate", "Landroidx/compose/runtime/MutableState;", "stateBalance", "Lkotlinx/coroutines/flow/StateFlow;", "getStateBalance", "()Lkotlinx/coroutines/flow/StateFlow;", "stateTerminals", "getStateTerminals", "balance", "Lkotlinx/coroutines/Job;", "clearBalance", "", "getTerminal", "compose-desktop-template"})
public final class MainViewModel extends com.myapp.util.ViewModel {
    private final com.myapp.actify.data.Interactor interactor = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> _stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isAuth$delegate = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.BalanceResponse>> _stateBalance = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.BalanceResponse>> stateBalance = null;
    
    @javax.inject.Inject
    public MainViewModel(@org.jetbrains.annotations.NotNull
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
}