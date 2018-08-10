package sampleproject.android.com.TestProject.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sampleproject.android.com.TestProject.BuildConfig;
import sampleproject.android.com.TestProject.MyApp;

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .client(MyApp.get().getHttpClient()).baseUrl(BuildConfig.BASE_URL).build();
        }
        return retrofit;
    }
}
