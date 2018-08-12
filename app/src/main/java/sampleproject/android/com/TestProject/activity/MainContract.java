package sampleproject.android.com.TestProject.activity;

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
