package sampleproject.android.com.TestProject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class WonderActivityModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "wonder_model_data")
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WonderActivityModel> CREATOR = new Creator<WonderActivityModel>() {
        @Override
        public WonderActivityModel createFromParcel(Parcel source) {
            WonderActivityModel var = new WonderActivityModel();
            var.data = source.createTypedArray(WonderActivityModelData.CREATOR);
            return var;
        }

        @Override
        public WonderActivityModel[] newArray(int size) {
            return new WonderActivityModel[size];
        }
    };
}
