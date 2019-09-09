package com.giussep.ricardo.napoleonsystems.dataSource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDao;
import com.giussep.ricardo.napoleonsystems.dataSource.local.User.UserDao;
import com.giussep.ricardo.napoleonsystems.model.Post;
import com.giussep.ricardo.napoleonsystems.model.UserDto;

@Database(entities = {Post.class, UserDto.class}, version = 4, exportSchema = false)
public abstract class NapoleonDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Post " +
                    "ADD favorite INTEGER NOT NULL DEFAULT 0;");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE User (" +
                    "id INTEGER PRIMARY KEY NOT NULL, " +
                    "name TEXT, " +
                    "user_name TEXT, " +
                    "email TEXT, " +
                    "street TEXT, " +
                    "suite TEXT, " +
                    "city TEXT, " +
                    "zip_code TEXT, " +
                    "lat TEXT, " +
                    "lng TEXT, " +
                    "phone TEXT, " +
                    "website TEXT, " +
                    "company_name TEXT, " +
                    "catch_phrase TEXT, " +
                    "bs TEXT " +
                    ");");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Post " +
                    "ADD leido INTEGER NOT NULL DEFAULT 1;");
        }
    };

    public abstract PostDao getPostDao();

    public abstract UserDao getUserDao();

}
