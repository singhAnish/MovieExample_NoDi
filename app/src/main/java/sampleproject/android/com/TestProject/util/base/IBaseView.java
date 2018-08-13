package sampleproject.android.com.TestProject.util.base;


import android.content.Context;

public interface IBaseView {

    void showDialog();

    void dismissDialog();

    void showToast(int msgID);

    void showToast(String msg);

    Context getContext();

    void showBackTitle(String title);
}
