package com.example.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendaodemo.db.DaoMaster;
import com.example.greendaodemo.db.DaoSession;

public class MyApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "secondes.db", null);

        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        writableDatabase.disableWriteAheadLogging();

        daoSession = new DaoMaster(writableDatabase).newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
