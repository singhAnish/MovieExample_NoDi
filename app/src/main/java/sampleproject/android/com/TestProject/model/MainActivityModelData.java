package sampleproject.android.com.TestProject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainActivityModelData implements Parcelable {

    private String place;
    private String url;

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

    public static final Creator<MainActivityModelData> CREATOR = new Creator<MainActivityModelData>() {
        @Override
        public MainActivityModelData createFromParcel(Parcel source) {
            MainActivityModelData var = new MainActivityModelData();
            var.place = source.readString();
            var.url = source.readString();
            return var;
        }

        @Override
        public MainActivityModelData[] newArray(int size) {
            return new MainActivityModelData[size];
        }
    };
}
