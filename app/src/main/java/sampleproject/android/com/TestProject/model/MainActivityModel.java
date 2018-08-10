package sampleproject.android.com.TestProject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainActivityModel implements Parcelable {

    private MainActivityModelData[] data;

    public MainActivityModelData[] getData() {
        return this.data;
    }

    public void setData(MainActivityModelData[] data) {
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

    public static final Creator<MainActivityModel> CREATOR = new Creator<MainActivityModel>() {
        @Override
        public MainActivityModel createFromParcel(Parcel source) {
            MainActivityModel var = new MainActivityModel();
            var.data = source.createTypedArray(MainActivityModelData.CREATOR);
            return var;
        }

        @Override
        public MainActivityModel[] newArray(int size) {
            return new MainActivityModel[size];
        }
    };
}
