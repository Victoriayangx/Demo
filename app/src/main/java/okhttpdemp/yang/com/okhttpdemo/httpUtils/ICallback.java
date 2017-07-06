package okhttpdemp.yang.com.okhttpdemo.httpUtils;

/**
 * Created by yang on 2017/7/3.
 */

public interface ICallback {
    void onSuccess(String result);
    void onFailure(String e);
}
