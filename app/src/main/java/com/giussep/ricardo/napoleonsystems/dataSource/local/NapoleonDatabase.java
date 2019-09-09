package com.giussep.ricardo.napoleonsystems.dataSource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDao;
import com.giussep.ricardo.napoleonsystems.model.Post;

@Database(entities = {Post.class}, version = 2, exportSchema = false)
public abstract class NapoleonDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Post " +
                    "ADD favorite INTEGER NOT NULL DEFAULT 0;");
        }
    };

    public abstract PostDao getPostDao();

}
