package sampleproject.android.com.TestProject.contract;

public interface WonderContract {

    //View Interface for WonderActivity
    interface View {
        void loadContentFromAPI();

        void updateGridView();
    }

    //Presenter Interface for WonderActivity
    interface Presenter{

        void loadData();

        void loadGridView();
    }
}
