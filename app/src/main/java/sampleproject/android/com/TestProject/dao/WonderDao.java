package sampleproject.android.com.TestProject.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import sampleproject.android.com.TestProject.model.WonderActivityModelData;

@Dao
public interface WonderDao {

    @Query("SELECT * FROM WonderActivityModelData")
    List<WonderActivityModelData> getWonderData();

    @Insert
    void insertWonderData(WonderActivityModelData... model);

    @Query("DELETE FROM WonderActivityModelData")
    void clearWonderData();
}
