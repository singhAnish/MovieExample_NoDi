package sampleproject.android.com.TestProject;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import sampleproject.android.com.TestProject.database.AppDatabase;
import sampleproject.android.com.TestProject.util.ConnectionDetector;
import sampleproject.android.com.TestProject.util.RetrofitInstance;
import timber.log.Timber;

public class MyApp extends Application {

    private static MyApp mInstance;
    private OkHttpClient okHttpClient;
    private AppDatabase db;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        getApplicationContext().registerReceiver(ConnectionDetector.getDetector().new NetworkReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        ConnectionDetector.getDetector().initConnectionState(mInstance);

        Timber.plant(new Timber.DebugTree());
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });

        okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();


        db = Room.databaseBuilder(getContext(), AppDatabase.class, "Development")
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized MyApp get() {
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return RetrofitInstance.getInstance();
    }

    public OkHttpClient getHttpClient(){
        return okHttpClient;
    }

    public Context getContext(){
        return mInstance;
    }

    public AppDatabase getDatabase(){
        return  db;
    }
}