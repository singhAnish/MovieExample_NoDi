package sampleproject.android.com.TestProject.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import sampleproject.android.com.TestProject.R;

public class ConnectionDetector {

    private static ConnectionDetector mConnectionDetector = null;
    private static boolean isConnected = false;

    /**
     * =======Private will stop to create a new object=====
     **/
    private ConnectionDetector() {
    }

    /***========return only one Instance of Connection Detector======*/
    public static synchronized ConnectionDetector getDetector() {
        if (mConnectionDetector == null) {
            mConnectionDetector = new ConnectionDetector();
        }
        return mConnectionDetector;
    }

    /**
     * =========If connection, then boolean will return true===========
     **/
    public static boolean isConnected() {
        if(!isConnected){
            Local.toastMessage(R.string.noInternet);
        }
        return isConnected;
    }

    /**
     * ==========Initialize connection State===========
     **/
    public void initConnectionState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }

    private void setIsConnectedToNetwork(boolean isConnect) {
        isConnected = isConnect;
    }

    public class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                ConnectionDetector.getDetector().setIsConnectedToNetwork(isConnected);
            }
        }
    }
}


