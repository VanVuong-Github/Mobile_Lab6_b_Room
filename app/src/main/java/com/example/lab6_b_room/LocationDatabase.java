package com.example.lab6_b_room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Location.class},version = 1, exportSchema = false)
public abstract class LocationDatabase extends RoomDatabase {
    private static LocationDatabase INSTANCE;
    private static final Object sLock = new Object();

    public abstract LocationDAO locationDAO();
    public static LocationDatabase getInMemoreyDatabase(Context context){
        if (INSTANCE == null) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),LocationDatabase.class)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

