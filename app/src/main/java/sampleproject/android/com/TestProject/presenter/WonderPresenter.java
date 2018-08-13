package sampleproject.android.com.TestProject.presenter;

import sampleproject.android.com.TestProject.MyApp;
import sampleproject.android.com.TestProject.contract.WonderContract.Presenter;
import sampleproject.android.com.TestProject.contract.WonderContract.View;
import sampleproject.android.com.TestProject.util.base.BasePresenter;

public class WonderPresenter extends BasePresenter<View> implements Presenter {

    public WonderPresenter(View view) {
        this.view = view;
        loadData();
    }

    @Override
    public void loadData() {
        if (view == null) {
            return;
        }
        if (MyApp.get().getDatabase().wonderDao().getWonderData().size() > 0) {
            loadGridView();
        } else {
            view.loadContentFromAPI();
        }
    }

    @Override
    public void loadGridView() {
        if (view == null) {
            return;
        }
        view.updateGridView();
    }
}
