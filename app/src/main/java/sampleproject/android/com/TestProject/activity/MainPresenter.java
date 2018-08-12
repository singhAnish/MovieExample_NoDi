package sampleproject.android.com.TestProject.activity;

import sampleproject.android.com.TestProject.activity.MainContract.Presenter;
import sampleproject.android.com.TestProject.activity.MainContract.View;
import sampleproject.android.com.TestProject.base.BasePresenter;
import sampleproject.android.com.TestProject.util.Prefs;

public class MainPresenter extends BasePresenter<View> implements Presenter {

    public MainPresenter(View view) {
        this.view = view;
        loadData();
    }

    @Override
    public void loadData(){
        if (view == null) {
            return;
        }
        if(Prefs.contains(Prefs.HOME_PAGE_DATA) && !Prefs.getStrPref(Prefs.HOME_PAGE_DATA).trim().isEmpty()){
            view.loadFromPrefs();
            loadGridView();
        }else{
            view.loadContentFromAPI();
        }
    }

    @Override
    public void loadGridView(){
        if (view == null) {
            return;
        }
        view.updateGridView();
    }
}
