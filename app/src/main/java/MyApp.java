import android.app.Application;

import okhttpdemp.yang.com.okhttpdemo.httpUtils.HttpHelper;
import okhttpdemp.yang.com.okhttpdemo.httpUtils.OkhttpProcessor;

/**
 * Created by yang on 2017/7/6.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new OkhttpProcessor());
    }
}
