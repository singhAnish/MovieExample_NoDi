package sampleproject.android.com.TestProject.activity;

import sampleproject.android.com.TestProject.activity.MainContract.Presenter;
import sampleproject.android.com.TestProject.activity.MainContract.View;
import sampleproject.android.com.TestProject.util.Prefs;

public class MainPresenter implements Presenter {

    private final View mView;

    public MainPresenter(View view) {
        mView = view;
        loadData();
    }

    private void loadData(){
        if(Prefs.contains(Prefs.HOME_PAGE_DATA) && !Prefs.getStrPref(Prefs.HOME_PAGE_DATA).trim().isEmpty()){
            mView.loadFromPrefs();
            loadGridView();
        }else{
            mView.loadContentFromAPI();
        }
    }

    @Override
    public void loadGridView(){
        mView.updateGridView();
    }
}
