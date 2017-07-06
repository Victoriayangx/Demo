package okhttpdemp.yang.com.okhttpdemo.httpUtils;

import java.util.Map;

/**
 * Created by yang on 2017/7/3.
 */

public interface IHttpProcessor {


    /**
     * 异步post请求
     */
    void post(String url, Map<String, Object> params, ICallback callback);

    /**
     * 异步get请求
     */
    void get(String url, Map<String, Object> params, ICallback callback);

    /**
     * 同步post请求
     */
    void postSyn(String url, Map<String, Object> params, ICallback callback);

    /**
     * 同步get请求
     */
    void getSyn(String url, Map<String, Object> params, ICallback callback);
}
