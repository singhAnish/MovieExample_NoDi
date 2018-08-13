package sampleproject.android.com.TestProject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import sampleproject.android.com.TestProject.dao.WonderDao;
import sampleproject.android.com.TestProject.model.WonderActivityModelData;

@Database(entities = {WonderActivityModelData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WonderDao wonderDao();

}
