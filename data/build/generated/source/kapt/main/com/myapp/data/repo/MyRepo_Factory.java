package com.myapp.data.repo;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MyRepo_Factory implements Factory<MyRepo> {
  @Override
  public MyRepo get() {
    return newInstance();
  }

  public static MyRepo_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MyRepo newInstance() {
    return new MyRepo();
  }

  private static final class InstanceHolder {
    private static final MyRepo_Factory INSTANCE = new MyRepo_Factory();
  }
}
