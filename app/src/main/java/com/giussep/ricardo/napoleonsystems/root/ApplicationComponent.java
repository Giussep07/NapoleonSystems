package com.giussep.ricardo.napoleonsystems.root;

import android.app.Application;

import com.giussep.ricardo.napoleonsystems.dataSource.local.NapoleonDatabaseModule;
import com.giussep.ricardo.napoleonsystems.home.HomeFragment;
import com.giussep.ricardo.napoleonsystems.home.HomeModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,
        HomeModule.class,
        NapoleonDatabaseModule.class})
public interface ApplicationComponent {

    void inject(HomeFragment homeFragment);
}
