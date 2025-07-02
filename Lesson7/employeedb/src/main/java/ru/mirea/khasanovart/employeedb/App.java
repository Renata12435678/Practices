package ru.mirea.khasanovart.employeedb;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    private static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        try {
            database = Room.databaseBuilder(this, AppDatabase.class, "superheroes-db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}