package sampleproject.android.com.TestProject.model;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"place"})
public class WonderActivityModelData {

    @NonNull
    private String place = "";
    private String url;

    public void setPlace(@NonNull String place) {
        this.place = place;
    }
    @NonNull
    public String getPlace() {
        return place;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}