package com.giussep.ricardo.napoleonsystems.dataSource.local;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDao;
import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDataSource;
import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostLocalDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class NapoleonDatabaseModule {

    private NapoleonDatabase database;

    public NapoleonDatabaseModule(Context context) {
        this.database = Room.databaseBuilder(context, NapoleonDatabase.class, "NapoleonDatabase")
                .addMigrations(NapoleonDatabase.MIGRATION_1_2)
                .build();
    }

    @Singleton
    @Provides
    NapoleonDatabase provideDb() {
        return database;
    }

    @Singleton
    @Provides
    PostDataSource providePostDataSource(NapoleonDatabase database) {
        return new PostLocalDataSource(database.getPostDao());
    }

    @Singleton
    @Provides
    PostDao providePostDao(NapoleonDatabase database) {
        return database.getPostDao();
    }
}
