package com.myapp.ui.navigation;

import java.lang.System;

/**
 * All navigation decisions are made from here
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u00012B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0017J\u0094\u0001\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H 0\u0014\"\b\b\u0000\u0010\u001f*\u00020!\"\b\b\u0001\u0010 *\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001f0$2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001f0&0$2\u000e\u0010\'\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u001f0(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\'\u0010-\u001a#\u0012\u0013\u0012\u0011H\u001f\u00a2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H 0.H\u0096\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\bX\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u00a8\u00063"}, d2 = {"Lcom/myapp/ui/navigation/NavHostComponent;", "Lcom/myapp/ui/navigation/Component;", "Lcom/arkivanov/decompose/ComponentContext;", "componentContext", "(Lcom/arkivanov/decompose/ComponentContext;)V", "appComponent", "Lcom/myapp/di/AppComponent;", "backPressedDispatcher", "Lcom/arkivanov/decompose/backpressed/BackPressedDispatcher;", "getBackPressedDispatcher", "()Lcom/arkivanov/decompose/backpressed/BackPressedDispatcher;", "instanceKeeper", "Lcom/arkivanov/decompose/instancekeeper/InstanceKeeper;", "getInstanceKeeper", "()Lcom/arkivanov/decompose/instancekeeper/InstanceKeeper;", "lifecycle", "Lcom/arkivanov/decompose/lifecycle/Lifecycle;", "getLifecycle", "()Lcom/arkivanov/decompose/lifecycle/Lifecycle;", "router", "Lcom/arkivanov/decompose/Router;", "Lcom/myapp/ui/navigation/NavHostComponent$Config;", "stateKeeper", "Lcom/arkivanov/decompose/statekeeper/StateKeeper;", "getStateKeeper", "()Lcom/arkivanov/decompose/statekeeper/StateKeeper;", "createScreenComponent", "config", "onSplashFinished", "", "render", "C", "T", "Lcom/arkivanov/decompose/statekeeper/Parcelable;", "", "initialConfiguration", "Lkotlin/Function0;", "initialBackStack", "", "configurationClass", "Lkotlin/reflect/KClass;", "key", "", "handleBackButton", "", "childFactory", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "configuration", "Config", "compose-desktop-template"})
public final class NavHostComponent implements com.myapp.ui.navigation.Component, com.arkivanov.decompose.ComponentContext {
    private final com.arkivanov.decompose.ComponentContext componentContext = null;
    private final com.myapp.di.AppComponent appComponent = null;
    
    /**
     * Router configuration
     */
    private final com.arkivanov.decompose.Router<com.myapp.ui.navigation.NavHostComponent.Config, com.myapp.ui.navigation.Component> router = null;
    
    public NavHostComponent(@org.jetbrains.annotations.NotNull()
    com.arkivanov.decompose.ComponentContext componentContext) {
        super();
    }
    
    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private final com.myapp.ui.navigation.Component createScreenComponent(com.myapp.ui.navigation.NavHostComponent.Config config, com.arkivanov.decompose.ComponentContext componentContext) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    @java.lang.Override()
    public void render() {
    }
    
    /**
     * Invoked when splash finish data sync
     */
    private final void onSplashFinished() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.arkivanov.decompose.backpressed.BackPressedDispatcher getBackPressedDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.arkivanov.decompose.instancekeeper.InstanceKeeper getInstanceKeeper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.arkivanov.decompose.lifecycle.Lifecycle getLifecycle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.arkivanov.decompose.statekeeper.StateKeeper getStateKeeper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <C extends com.arkivanov.decompose.statekeeper.Parcelable, T extends java.lang.Object>com.arkivanov.decompose.Router<C, T> router(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends C> initialConfiguration, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends java.util.List<? extends C>> initialBackStack, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<? extends C> configurationClass, @org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean handleBackButton, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super C, ? super com.arkivanov.decompose.ComponentContext, ? extends T> childFactory) {
        return null;
    }
    
    /**
     * Available screensSelectApp
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/myapp/ui/navigation/NavHostComponent$Config;", "Lcom/arkivanov/decompose/statekeeper/Parcelable;", "()V", "Main", "Splash", "Lcom/myapp/ui/navigation/NavHostComponent$Config$Splash;", "Lcom/myapp/ui/navigation/NavHostComponent$Config$Main;", "compose-desktop-template"})
    static abstract class Config implements com.arkivanov.decompose.statekeeper.Parcelable {
        
        private Config() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/myapp/ui/navigation/NavHostComponent$Config$Splash;", "Lcom/myapp/ui/navigation/NavHostComponent$Config;", "()V", "compose-desktop-template"})
        public static final class Splash extends com.myapp.ui.navigation.NavHostComponent.Config {
            @org.jetbrains.annotations.NotNull()
            public static final com.myapp.ui.navigation.NavHostComponent.Config.Splash INSTANCE = null;
            
            private Splash() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/myapp/ui/navigation/NavHostComponent$Config$Main;", "Lcom/myapp/ui/navigation/NavHostComponent$Config;", "()V", "compose-desktop-template"})
        public static final class Main extends com.myapp.ui.navigation.NavHostComponent.Config {
            @org.jetbrains.annotations.NotNull()
            public static final com.myapp.ui.navigation.NavHostComponent.Config.Main INSTANCE = null;
            
            private Main() {
                super();
            }
        }
    }
}