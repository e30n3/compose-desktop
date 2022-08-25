package com.myapp.ui.feature.main;

import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.myapp.actify.data.Interactor;
import com.myapp.actify.di.AppComponent;
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse;
import com.myapp.ui.feature.action.PrizesViewModel;
import com.myapp.ui.feature.drawer.activate.ActivateTerminalViewModel;
import com.myapp.ui.feature.drawer.report.ReportViewModel;
import com.myapp.ui.feature.option.OptionViewModel;
import com.myapp.util.ViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import ru.involta.actify.domain.Result;
import ru.involta.actify.ui.screen.main.nested.*;
import ru.involta.actify.ui.screen.viewmodel.AccrueViewModel;
import ru.involta.actify.ui.screen.viewmodel.DebitViewModel;
import ru.involta.actify.ui.screen.viewmodel.RegistrationViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001.B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020,R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0015\u0010\u001b\u001a\u00060\u001cR\u00020\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R+\u0010\"\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020!8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\u0013\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*\u00a8\u0006/"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel;", "Lcom/myapp/util/ViewModel;", "interactor", "Lcom/myapp/actify/data/Interactor;", "appComponent", "Lcom/myapp/actify/di/AppComponent;", "(Lcom/myapp/actify/data/Interactor;Lcom/myapp/actify/di/AppComponent;)V", "_stateTerminals", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/involta/actify/domain/Result;", "Lcom/myapp/actify/domain/entity/api/response/TerminalRegistrationResponse;", "<set-?>", "", "card", "getCard", "()Ljava/lang/String;", "setCard", "(Ljava/lang/String;)V", "card$delegate", "Landroidx/compose/runtime/MutableState;", "currentScreen", "Landroidx/compose/runtime/MutableState;", "Lcom/myapp/ui/feature/main/ActionScreen;", "getCurrentScreen", "()Landroidx/compose/runtime/MutableState;", "setCurrentScreen", "(Landroidx/compose/runtime/MutableState;)V", "innerViewModels", "Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "getInnerViewModels", "()Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "getInteractor", "()Lcom/myapp/actify/data/Interactor;", "", "isAuth", "()Z", "setAuth", "(Z)V", "isAuth$delegate", "stateTerminals", "Lkotlinx/coroutines/flow/StateFlow;", "getStateTerminals", "()Lkotlinx/coroutines/flow/StateFlow;", "getTerminal", "Lkotlinx/coroutines/Job;", "logOut", "RenderDrawer", "compose-desktop-template"})
public final class MainViewModel extends com.myapp.util.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.myapp.actify.data.Interactor interactor = null;
    @org.jetbrains.annotations.NotNull
    private final com.myapp.ui.feature.main.MainViewModel.RenderDrawer innerViewModels = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.involta.actify.domain.Result<com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse>> _stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.involta.actify.domain.Result<com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse>> stateTerminals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState card$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState isAuth$delegate = null;
    @org.jetbrains.annotations.NotNull
    private androidx.compose.runtime.MutableState<com.myapp.ui.feature.main.ActionScreen> currentScreen;
    
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
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCard() {
        return null;
    }
    
    public final void setCard(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean isAuth() {
        return false;
    }
    
    public final void setAuth(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<com.myapp.ui.feature.main.ActionScreen> getCurrentScreen() {
        return null;
    }
    
    public final void setCurrentScreen(@org.jetbrains.annotations.NotNull
    androidx.compose.runtime.MutableState<com.myapp.ui.feature.main.ActionScreen> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getTerminal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job logOut() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0007J \u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bH\u0007J\b\u0010\u001c\u001a\u00020\u0016H\u0007J\b\u0010\u001d\u001a\u00020\u0016H\u0007J\b\u0010\u001e\u001a\u00020\u0016H\u0007J\b\u0010\u001f\u001a\u00020\u0016H\u0007J\u0012\u0010 \u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/myapp/ui/feature/main/MainViewModel$RenderDrawer;", "", "interactor", "Lcom/myapp/actify/data/Interactor;", "(Lcom/myapp/ui/feature/main/MainViewModel;Lcom/myapp/actify/data/Interactor;)V", "accrueViewModel", "Lru/involta/actify/ui/screen/viewmodel/AccrueViewModel;", "activateViewModel", "Lcom/myapp/ui/feature/drawer/activate/ActivateTerminalViewModel;", "debitViewModel", "Lru/involta/actify/ui/screen/viewmodel/DebitViewModel;", "getInteractor", "()Lcom/myapp/actify/data/Interactor;", "optionViewModel", "Lcom/myapp/ui/feature/option/OptionViewModel;", "prizesViewModel", "Lcom/myapp/ui/feature/action/PrizesViewModel;", "registerViewModel", "Lru/involta/actify/ui/screen/viewmodel/RegistrationViewModel;", "reportViewModel", "Lcom/myapp/ui/feature/drawer/report/ReportViewModel;", "renderAccrue", "", "renderActivate", "modifier", "Landroidx/compose/ui/Modifier;", "onSuccess", "Lkotlin/Function0;", "renderDebit", "renderOption", "renderPrizes", "renderRegister", "renderReport", "compose-desktop-template"})
    public final class RenderDrawer {
        @org.jetbrains.annotations.NotNull
        private final com.myapp.actify.data.Interactor interactor = null;
        private com.myapp.ui.feature.drawer.report.ReportViewModel reportViewModel;
        private com.myapp.ui.feature.option.OptionViewModel optionViewModel;
        private ru.involta.actify.ui.screen.viewmodel.AccrueViewModel accrueViewModel;
        private ru.involta.actify.ui.screen.viewmodel.DebitViewModel debitViewModel;
        private com.myapp.ui.feature.action.PrizesViewModel prizesViewModel;
        private ru.involta.actify.ui.screen.viewmodel.RegistrationViewModel registerViewModel;
        private com.myapp.ui.feature.drawer.activate.ActivateTerminalViewModel activateViewModel;
        
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
        public final void renderAccrue() {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderDebit() {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderPrizes() {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderRegister() {
        }
        
        @androidx.compose.runtime.Composable
        public final void renderActivate(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
        }
    }
}