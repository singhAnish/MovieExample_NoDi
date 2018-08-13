package sampleproject.android.com.TestProject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class WonderActivityModelData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "wonder_place")
    private String place;

    @ColumnInfo(name = "wonder_url")
    private String url;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.place);
        dest.writeString(this.url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WonderActivityModelData> CREATOR = new Creator<WonderActivityModelData>() {
        @Override
        public WonderActivityModelData createFromParcel(Parcel source) {
            WonderActivityModelData var = new WonderActivityModelData();
            var.place = source.readString();
            var.url = source.readString();
            return var;
        }

        @Override
        public WonderActivityModelData[] newArray(int size) {
            return new WonderActivityModelData[size];
        }
    };
}
