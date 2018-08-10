package sampleproject.android.com.TestProject.activity;

import sampleproject.android.com.TestProject.util.Prefs;

public class MainPresenter {
    private final MainView mView;

    protected MainPresenter(MainView view) {
        mView = view;
        if(Prefs.contains(Prefs.HOME_PAGE_DATA) && !Prefs.getStrPref(Prefs.HOME_PAGE_DATA).trim().isEmpty()){
            mView.initPage();
            updateGridView();
        }else{
            mView.loadContentFromAPI();
        }
    }

    public void updateGridView(){
        mView.updateGridView();
    }
}
