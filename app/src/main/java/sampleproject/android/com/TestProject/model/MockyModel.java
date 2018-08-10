package sampleproject.android.com.TestProject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MockyModel implements Parcelable {

    private MockyModelData[] data;

    public MockyModelData[] getData() {
        return this.data;
    }

    public void setData(MockyModelData[] data) {
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

    public static final Creator<MockyModel> CREATOR = new Creator<MockyModel>() {
        @Override
        public MockyModel createFromParcel(Parcel source) {
            MockyModel var = new MockyModel();
            var.data = source.createTypedArray(MockyModelData.CREATOR);
            return var;
        }

        @Override
        public MockyModel[] newArray(int size) {
            return new MockyModel[size];
        }
    };
}
