package com.giussep.ricardo.napoleonsystems.root;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.giussep.ricardo.napoleonsystems.dataSource.local.NapoleonDatabaseModule;
import com.giussep.ricardo.napoleonsystems.home.HomeModule;

public class ApplicationClass extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .homeModule(new HomeModule())
                .napoleonDatabaseModule(new NapoleonDatabaseModule(this))
                .build();

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
