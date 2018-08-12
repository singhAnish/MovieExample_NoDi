package sampleproject.android.com.TestProject.presenter;

import sampleproject.android.com.TestProject.contract.MainContract.Presenter;
import sampleproject.android.com.TestProject.contract.MainContract.View;
import sampleproject.android.com.TestProject.util.Prefs;
import sampleproject.android.com.TestProject.util.base.BasePresenter;

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
