package sampleproject.android.com.TestProject.base;


import android.content.Context;

public interface IBaseView {

    void showDialog();

    void dismissDialog();

    void showToast(int msgID);

    void showStringToast(String msg);

    Context getContext();

    void showBackTitle(String title);
}
