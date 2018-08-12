package sampleproject.android.com.TestProject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sampleproject.android.com.TestProject.MyApp;
import sampleproject.android.com.TestProject.R;
import sampleproject.android.com.TestProject.adapter.MainAdapter;
import sampleproject.android.com.TestProject.contract.MainContract.View;
import sampleproject.android.com.TestProject.model.MainActivityModel;
import sampleproject.android.com.TestProject.model.MainActivityModelData;
import sampleproject.android.com.TestProject.presenter.MainPresenter;
import sampleproject.android.com.TestProject.util.ConnectionDetector;
import sampleproject.android.com.TestProject.util.Local;
import sampleproject.android.com.TestProject.util.Prefs;
import sampleproject.android.com.TestProject.util.apiInterface.APIInterface;
import sampleproject.android.com.TestProject.util.base.BaseActivity;

public class MainActivity extends BaseActivity implements View {

    private MainPresenter mPresenter;
    private ArrayList<MainActivityModelData> mModel;
    private RecyclerView mRecycler;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showBackTitle(Local.getString(R.string.app_name));
        mRecycler = findViewById(R.id.recyclerView);
        mPresenter = new MainPresenter(this);
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
    public void loadFromPrefs() {
        String mJsonString = Prefs.getStrPref(Prefs.HOME_PAGE_DATA);
        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(mJsonString);
        Gson gson = new Gson();

        MainActivityModel model = gson.fromJson(mJson, MainActivityModel.class);
        if (mModel == null) {
            mModel = new ArrayList<>();
        }
        mModel.addAll(Arrays.asList(model.getData()));
    }

    @Override
    public void loadContentFromAPI() {
        if (ConnectionDetector.isConnected()) {
            showDialog();
            APIInterface service = MyApp.get().getRetrofit().create(APIInterface.class);
            Call<MainActivityModel> call = service.getMovieListData();
            call.enqueue(new Callback<MainActivityModel>() {
                @Override
                public void onResponse(Call<MainActivityModel> call, Response<MainActivityModel> response) {
                    if (response.isSuccessful()) {
                        dismissDialog();
                        Gson gson = new Gson();
                        String responseData = gson.toJson(response.body());
                        Prefs.setStrPref(Prefs.HOME_PAGE_DATA, responseData);

                        MainActivityModel movieList = response.body();
                        if (mModel == null) {
                            mModel = new ArrayList<>();
                        }
                        mModel.addAll(Arrays.asList(movieList.getData()));
                        mPresenter.loadGridView();
                    } else {
                        showToast(R.string.somethingWrong);
                    }
                }

                @Override
                public void onFailure(Call<MainActivityModel> call, Throwable t) {
                    dismissDialog();
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void updateGridView() {
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        MainAdapter mAdapter = new MainAdapter(mModel);
        mRecycler.setAdapter(mAdapter);
    }
}
