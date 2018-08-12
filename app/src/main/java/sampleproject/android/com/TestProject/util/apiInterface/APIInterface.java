package sampleproject.android.com.TestProject.util.apiInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import sampleproject.android.com.TestProject.BuildConfig;
import sampleproject.android.com.TestProject.model.MainActivityModel;

public interface APIInterface {

    @GET(BuildConfig.KEY)
    Call<MainActivityModel> getMovieListData();

}
