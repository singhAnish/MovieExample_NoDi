package sampleproject.android.com.TestProject.util;

import android.content.Context;
import android.content.SharedPreferences;

import sampleproject.android.com.TestProject.MyApp;

public class Prefs {
    public final static String HOME_PAGE_DATA = "home_page_data";

    private static SharedPreferences getPrefs() {
        return MyApp.get().getContext().getSharedPreferences("MVP_TEST_PREFS", Context.MODE_PRIVATE);
    }

    public static boolean contains(String key) {
        return getPrefs().contains(key);
    }

    public static void setStrPref(String key, String value) {
        getPrefs().edit().putString(key, value).apply();
    }

    public static String getStrPref(String key) {
        return getPrefs().getString(key, "");
    }

    public static void removePref(String key) {
        getPrefs().edit().remove(key).apply();
    }
}