package com.myapp.ui.feature.main;

import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.myapp.actify.data.Interactor;
import com.myapp.actify.di.AppComponent;
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse;
import com.myapp.ui.feature.drawer.activate.ActivateTerminalViewModel;
import com.myapp.ui.feature.drawer.report.ReportViewModel;
import com.myapp.ui.feature.option.OptionViewModel;
import com.myapp.util.ViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import ru.involta.actify.domain.Result;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001 B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R+\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006!"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel;", "Lcom/myapp/util/ViewModel;", "interactor", "Lcom/myapp/actify/data/Interactor;", "appComponent", "Lcom/myapp/actify/di/AppComponent;", "(Lcom/myapp/actify/data/Interactor;Lcom/myapp/actify/di/AppComponent;)V", "_stateTerminals", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/involta/actify/domain/Result;", "Lcom/myapp/actify/domain/entity/api/response/TerminalRegistrationResponse;", "innerViewModels", "Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "getInnerViewModels", "()Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "getInteractor", "()Lcom/myapp/actify/data/Interactor;", "<set-?>", "", "isAuth", "()Z", "setAuth", "(Z)V", "isAuth$delegate", "Landroidx/compose/runtime/MutableState;", "stateTerminals", "Lkotlinx/coroutines/flow/StateFlow;", "getStateTerminals", "()Lkotlinx/coroutines/flow/StateFlow;", "getTerminal", "Lkotlinx/coroutines/Job;", "logOut", "RenderDrawer", "compose-desktop-template"})
public final class MainViewModel extends com.myapp.util.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.myapp.actify.data.Interactor interactor = null;
    @org.jetbrains.annotations.NotNull
    private final com.myapp.ui.feature.main.MainViewModel.RenderDrawer innerViewModels = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse>> _stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse>> stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isAuth$delegate = null;
    
    @javax.inject.Inject
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.myapp.actify.data.Interactor interactor, @org.jetbrains.annotations.NotNull
    com.myapp.actify.di.AppComponent appComponent) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myapp.actify.data.Interactor getInteractor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myapp.ui.feature.main.MainViewModel.RenderDrawer getInnerViewModels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse>> getStateTerminals() {
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
    public final kotlinx.coroutines.Job logOut() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0007J\b\u0010\r\u001a\u00020\bH\u0007J\u0012\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "", "interactor", "Lcom/myapp/actify/data/Interactor;", "(Lcom/myapp/actify/data/Interactor;)V", "getInteractor", "()Lcom/myapp/actify/data/Interactor;", "renderActivate", "", "modifier", "Landroidx/compose/ui/Modifier;", "onSuccess", "Lkotlin/Function0;", "renderOption", "renderReport", "compose-desktop-template"})
    public static final class RenderDrawer {
        @org.jetbrains.annotations.NotNull
        private final com.myapp.actify.data.Interactor interactor = null;
        
        public RenderDrawer(@org.jetbrains.annotations.NotNull
        com.myapp.actify.data.Interactor interactor) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myapp.actify.data.Interactor getInteractor() {
            return null;
        }
        
        @androidx.compose.runtime.Composable
        public final void renderReport(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier) {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderOption() {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderActivate(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
        }
    }
}