package com.myapp.ui.feature.main;

import androidx.compose.runtime.Composable;
import com.arkivanov.decompose.ComponentContext;
import com.myapp.actify.di.AppComponent;
import com.myapp.ui.navigation.Component;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001d\u001a\u00020\u001eH\u0017J\u0094\u0001\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\"0 \"\b\b\u0000\u0010!*\u00020#\"\b\b\u0001\u0010\"*\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0&2\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0(0&2\u000e\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u0002H!0*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\'\u0010/\u001a#\u0012\u0013\u0012\u0011H!\u00a2\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\"00H\u0096\u0001R\u0012\u0010\u0007\u001a\u00020\bX\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u00064"}, d2 = {"Lcom/myapp/ui/feature/main/MainScreenComponent;", "Lcom/myapp/ui/navigation/Component;", "Lcom/arkivanov/decompose/ComponentContext;", "appComponent", "Lcom/myapp/actify/di/AppComponent;", "componentContext", "(Lcom/myapp/actify/di/AppComponent;Lcom/arkivanov/decompose/ComponentContext;)V", "backPressedDispatcher", "Lcom/arkivanov/decompose/backpressed/BackPressedDispatcher;", "getBackPressedDispatcher", "()Lcom/arkivanov/decompose/backpressed/BackPressedDispatcher;", "instanceKeeper", "Lcom/arkivanov/decompose/instancekeeper/InstanceKeeper;", "getInstanceKeeper", "()Lcom/arkivanov/decompose/instancekeeper/InstanceKeeper;", "lifecycle", "Lcom/arkivanov/decompose/lifecycle/Lifecycle;", "getLifecycle", "()Lcom/arkivanov/decompose/lifecycle/Lifecycle;", "stateKeeper", "Lcom/arkivanov/decompose/statekeeper/StateKeeper;", "getStateKeeper", "()Lcom/arkivanov/decompose/statekeeper/StateKeeper;", "viewModel", "Lcom/myapp/ui/feature/main/MainViewModel;", "getViewModel", "()Lcom/myapp/ui/feature/main/MainViewModel;", "setViewModel", "(Lcom/myapp/ui/feature/main/MainViewModel;)V", "render", "", "router", "Lcom/arkivanov/decompose/Router;", "C", "T", "Lcom/arkivanov/decompose/statekeeper/Parcelable;", "", "initialConfiguration", "Lkotlin/Function0;", "initialBackStack", "", "configurationClass", "Lkotlin/reflect/KClass;", "key", "", "handleBackButton", "", "childFactory", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "configuration", "compose-desktop-template"})
public final class MainScreenComponent implements com.myapp.ui.navigation.Component, com.arkivanov.decompose.ComponentContext {
    private final com.arkivanov.decompose.ComponentContext componentContext = null;
    @javax.inject.Inject
    public com.myapp.ui.feature.main.MainViewModel viewModel;
    
    public MainScreenComponent(@org.jetbrains.annotations.NotNull
    com.myapp.actify.di.AppComponent appComponent, @org.jetbrains.annotations.NotNull
    com.arkivanov.decompose.ComponentContext componentContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myapp.ui.feature.main.MainViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull
    com.myapp.ui.feature.main.MainViewModel p0) {
    }
    
    @androidx.compose.runtime.Composable
    @java.lang.Override
    public void render() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.arkivanov.decompose.backpressed.BackPressedDispatcher getBackPressedDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.arkivanov.decompose.instancekeeper.InstanceKeeper getInstanceKeeper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.arkivanov.decompose.lifecycle.Lifecycle getLifecycle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.arkivanov.decompose.statekeeper.StateKeeper getStateKeeper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public <C extends com.arkivanov.decompose.statekeeper.Parcelable, T extends java.lang.Object>com.arkivanov.decompose.Router<C, T> router(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<? extends C> initialConfiguration, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<? extends java.util.List<? extends C>> initialBackStack, @org.jetbrains.annotations.NotNull
    kotlin.reflect.KClass<? extends C> configurationClass, @org.jetbrains.annotations.NotNull
    java.lang.String key, boolean handleBackButton, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super C, ? super com.arkivanov.decompose.ComponentContext, ? extends T> childFactory) {
        return null;
    }
}