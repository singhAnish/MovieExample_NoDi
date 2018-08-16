package sampleproject.android.com.TestProject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sampleproject.android.com.TestProject.MyApp;
import sampleproject.android.com.TestProject.R;
import sampleproject.android.com.TestProject.adapter.WonderAdapter;
import sampleproject.android.com.TestProject.contract.WonderContract.View;
import sampleproject.android.com.TestProject.model.WonderActivityModel;
import sampleproject.android.com.TestProject.model.WonderActivityModelData;
import sampleproject.android.com.TestProject.presenter.WonderPresenter;
import sampleproject.android.com.TestProject.util.ConnectionDetector;
import sampleproject.android.com.TestProject.util.Local;
import sampleproject.android.com.TestProject.util.apiInterface.APIInterface;
import sampleproject.android.com.TestProject.util.base.BaseActivity;

public class WonderActivity extends BaseActivity implements View {

    private WonderPresenter mPresenter;
    private RecyclerView mRecycler;

    @Override
    protected int getContentView() {
        return R.layout.activity_wonder;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showBackTitle(Local.getString(R.string.app_name));
        mRecycler = findViewById(R.id.recyclerView);
        mPresenter = new WonderPresenter(this);
    }

    @Override
    public void onPause() {
        mPresenter.onViewInactive();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewActive(this);
    }

    @Override
    public void loadContentFromAPI() {
        if (ConnectionDetector.isConnected()) {
            showDialog();
            APIInterface service = MyApp.get().getRetrofit().create(APIInterface.class);
            Call<WonderActivityModel> call = service.getMovieListData();
            call.enqueue(new Callback<WonderActivityModel>() {
                @Override
                public void onResponse(Call<WonderActivityModel> call, Response<WonderActivityModel> response) {
                    if (response.isSuccessful()) {
                        dismissDialog();
                        MyApp.get().getDatabase().wonderDao().clearWonderData();

                        WonderActivityModel movieList = response.body();
                        ArrayList<WonderActivityModelData> mModel = new ArrayList<>(Arrays.asList(movieList.getData()));
                        for (int i = 0; i < mModel.size(); i++) {
                            MyApp.get().getDatabase().wonderDao().insertWonderData(mModel.get(i));
                        }
                        mPresenter.loadGridView();
                    } else {
                        showToast(R.string.somethingWrong);
                    }
                }

                @Override
                public void onFailure(Call<WonderActivityModel> call, Throwable t) {
                    dismissDialog();
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void updateGridView() {
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        WonderAdapter mAdapter = new WonderAdapter();
        mRecycler.setAdapter(mAdapter);
    }
}
