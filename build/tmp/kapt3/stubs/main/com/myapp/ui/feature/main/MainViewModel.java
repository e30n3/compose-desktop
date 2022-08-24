package com.myapp.ui.feature.main;

import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.myapp.actify.data.Interactor;
import com.myapp.actify.di.AppComponent;
import com.myapp.ui.feature.drawer.*;
import com.myapp.util.ViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import ru.involta.actify.domain.Result;
import ru.involta.actify.domain.entity.api.response.BalanceResponse;
import ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001b\u001a\u00020\u001cR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001e"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel;", "Lcom/myapp/util/ViewModel;", "interactor", "Lcom/myapp/actify/data/Interactor;", "appComponent", "Lcom/myapp/actify/di/AppComponent;", "(Lcom/myapp/actify/data/Interactor;Lcom/myapp/actify/di/AppComponent;)V", "_stateTerminals", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/involta/actify/domain/Result;", "Lru/involta/actify/domain/entity/api/response/TerminalRegistrationResponse;", "innerViewModels", "Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "getInnerViewModels", "()Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "<set-?>", "", "isAuth", "()Z", "setAuth", "(Z)V", "isAuth$delegate", "Landroidx/compose/runtime/MutableState;", "stateTerminals", "Lkotlinx/coroutines/flow/StateFlow;", "getStateTerminals", "()Lkotlinx/coroutines/flow/StateFlow;", "getTerminal", "Lkotlinx/coroutines/Job;", "RenderDrawer", "compose-desktop-template"})
public final class MainViewModel extends com.myapp.util.ViewModel {
    private final com.myapp.actify.data.Interactor interactor = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> _stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<ru.involta.actify.domain.entity.api.response.TerminalRegistrationResponse>> stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isAuth$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final com.myapp.ui.feature.main.MainViewModel.RenderDrawer innerViewModels = null;
    
    @javax.inject.Inject
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.myapp.actify.data.Interactor interactor, @org.jetbrains.annotations.NotNull
    com.myapp.actify.di.AppComponent appComponent) {
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
    public final com.myapp.ui.feature.main.MainViewModel.RenderDrawer getInnerViewModels() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cH\u0007J\u0012\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007J&\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cH\u0007R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006 "}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "", "appComponent", "Lcom/myapp/actify/di/AppComponent;", "(Lcom/myapp/actify/di/AppComponent;)V", "activateViewModel", "Lcom/myapp/ui/feature/drawer/ActivateTerminalViewModel;", "getActivateViewModel", "()Lcom/myapp/ui/feature/drawer/ActivateTerminalViewModel;", "setActivateViewModel", "(Lcom/myapp/ui/feature/drawer/ActivateTerminalViewModel;)V", "reportViewModel", "Lcom/myapp/ui/feature/drawer/ReportViewModel;", "getReportViewModel", "()Lcom/myapp/ui/feature/drawer/ReportViewModel;", "setReportViewModel", "(Lcom/myapp/ui/feature/drawer/ReportViewModel;)V", "statusViewModel", "Lcom/myapp/ui/feature/drawer/TerminalStatusViewModel;", "getStatusViewModel", "()Lcom/myapp/ui/feature/drawer/TerminalStatusViewModel;", "setStatusViewModel", "(Lcom/myapp/ui/feature/drawer/TerminalStatusViewModel;)V", "renderActivate", "", "modifier", "Landroidx/compose/ui/Modifier;", "onSuccess", "Lkotlin/Function0;", "renderReport", "renderStatus", "onExit", "compose-desktop-template"})
    public static final class RenderDrawer {
        @javax.inject.Inject
        public com.myapp.ui.feature.drawer.ReportViewModel reportViewModel;
        @javax.inject.Inject
        public com.myapp.ui.feature.drawer.ActivateTerminalViewModel activateViewModel;
        @javax.inject.Inject
        public com.myapp.ui.feature.drawer.TerminalStatusViewModel statusViewModel;
        
        public RenderDrawer(@org.jetbrains.annotations.NotNull
        com.myapp.actify.di.AppComponent appComponent) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myapp.ui.feature.drawer.ReportViewModel getReportViewModel() {
            return null;
        }
        
        public final void setReportViewModel(@org.jetbrains.annotations.NotNull
        com.myapp.ui.feature.drawer.ReportViewModel p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myapp.ui.feature.drawer.ActivateTerminalViewModel getActivateViewModel() {
            return null;
        }
        
        public final void setActivateViewModel(@org.jetbrains.annotations.NotNull
        com.myapp.ui.feature.drawer.ActivateTerminalViewModel p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myapp.ui.feature.drawer.TerminalStatusViewModel getStatusViewModel() {
            return null;
        }
        
        public final void setStatusViewModel(@org.jetbrains.annotations.NotNull
        com.myapp.ui.feature.drawer.TerminalStatusViewModel p0) {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderReport(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier) {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderActivate(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
        }
        
        @org.jetbrains.annotations.NotNull
        @androidx.compose.runtime.Composable
        public final kotlin.jvm.functions.Function0<kotlin.Unit> renderStatus(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onExit) {
            return null;
        }
    }
}