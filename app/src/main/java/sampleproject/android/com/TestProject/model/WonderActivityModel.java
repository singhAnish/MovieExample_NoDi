package sampleproject.android.com.TestProject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WonderActivityModel{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private WonderActivityModelData[] data;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public WonderActivityModelData[] getData() {
        return this.data;
    }

    public void setData(WonderActivityModelData[] data) {
        this.data = data;
    }
}
