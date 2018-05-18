package tdc.edu.vn.shoesshop.Toan;

import android.app.Application;

public class MyApplication extends Application {
    static MyApplication wifiInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return wifiInstance;
    }

}