package com.example.endemikdb.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.model.Favorit;

@Database(entities = {Endemik.class, Favorit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EndemikDao endemikDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "endemik_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}