package com.giussep.ricardo.napoleonsystems.root;

import android.app.Application;
import android.content.Context;

import com.giussep.ricardo.napoleonsystems.BuildConfig;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class ApplicationModule {

    private ApplicationClass applicationClass;

    ApplicationModule(ApplicationClass applicationClass) {
        this.applicationClass = applicationClass;
    }

    @Singleton
    @Provides
    Context bindContext() {
        return applicationClass;
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }
}
