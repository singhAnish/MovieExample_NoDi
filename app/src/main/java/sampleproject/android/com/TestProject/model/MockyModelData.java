package sampleproject.android.com.TestProject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MockyModelData implements Parcelable {
    public static final Creator<MockyModelData> CREATOR = new Creator<MockyModelData>() {
        @Override
        public MockyModelData createFromParcel(Parcel source) {
            MockyModelData var = new MockyModelData();
            var.place = source.readString();
            var.url = source.readString();
            return var;
        }

        @Override
        public MockyModelData[] newArray(int size) {
            return new MockyModelData[size];
        }
    };
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
}
