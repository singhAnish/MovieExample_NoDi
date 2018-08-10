package sampleproject.android.com.TestProject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sampleproject.android.com.TestProject.MyApp;
import sampleproject.android.com.TestProject.R;
import sampleproject.android.com.TestProject.adapter.MockyAdapter;
import sampleproject.android.com.TestProject.base.BaseActivity;
import sampleproject.android.com.TestProject.interfaces.APIInterface;
import sampleproject.android.com.TestProject.model.MockyModel;
import sampleproject.android.com.TestProject.model.MockyModelData;
import sampleproject.android.com.TestProject.util.ConnectionDetector;
import sampleproject.android.com.TestProject.util.Local;
import sampleproject.android.com.TestProject.util.Prefs;

public class MainActivity extends BaseActivity implements MainView {

    private MainPresenter mPresenter;
    private ArrayList<MockyModelData> mModel;
    private Unbinder unbinder;
    @BindView(R.id.recyclerView) RecyclerView mRecycler;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showBackTitle(Local.getString(R.string.app_name));
        unbinder = ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void initPage() {
        Local.toastStringMessage("Loading data from Prefs");
        String mJsonString = Prefs.getStrPref(Prefs.HOME_PAGE_DATA);
        JsonParser parser = new JsonParser();
        JsonElement mJson =  parser.parse(mJsonString);
        Gson gson = new Gson();

        MockyModel model = gson.fromJson(mJson, MockyModel.class);
        if (mModel == null) {
            mModel = new ArrayList<>();
        }
        mModel.addAll(Arrays.asList(model.getData()));
    }

    @Override
    public void loadContentFromAPI(){
        Local.toastStringMessage("Loading data from API");
        if (ConnectionDetector.isConnected()) {
            showDialog();
            APIInterface service = MyApp.get().getRetrofit().create(APIInterface.class);
            Call<MockyModel> call = service.getMovieListData();
            call.enqueue(new Callback<MockyModel>() {
                @Override
                public void onResponse(Call<MockyModel> call, Response<MockyModel> response) {
                    if (response.isSuccessful()) {
                        dismissDialog();
                        Local.logMessage("url : " + response.raw().request().url());
                        Gson gson = new Gson();
                        String responseData = gson.toJson(response.body());
                        Prefs.setStrPref(Prefs.HOME_PAGE_DATA, responseData);

                        MockyModel movieList = response.body();
                        if (mModel == null) {
                            mModel = new ArrayList<>();
                        }
                        mModel.addAll(Arrays.asList(movieList.getData()));
                        mPresenter.updateGridView();
                    } else {
                        Local.toastMessage(R.string.somethingWrong);
                    }
                }

                @Override
                public void onFailure(Call<MockyModel> call, Throwable t) {
                    dismissDialog();
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void updateGridView() {
        GridLayoutManager layoutManager = new GridLayoutManager(MyApp.get().getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        MockyAdapter mAdapter = new MockyAdapter(mModel);
        mRecycler.setAdapter(mAdapter);
    }
}
