package sampleproject.android.com.TestProject.contract;

public interface MainContract {

    //View Interface for MainActivity
    interface View {
        void loadFromPrefs();

        void loadContentFromAPI();

        void updateGridView();
    }

    //Presenter Interface for MainActivity
    interface Presenter{

        void loadData();

        void loadGridView();
    }
}
