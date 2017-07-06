package okhttpdemp.yang.com.okhttpdemo.httpUtils;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by yang on 2017/7/3.
 */

public class OkhttpProcessor implements IHttpProcessor {


    private static final String TAG = "OkhttpProcessor";
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;


    public OkhttpProcessor() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写的超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .build();

        mHandler = new Handler();
    }

    /**
     * 异步post
     */

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestBody requestBody = appendBody(params);
        Request request = addHeaders().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                failedCallBack(e.toString(), callback);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                responseCallBack(response, callback);
            }
        });
    }

    /**
     * 异步get
     */
    @Override
    public void get(String url, Map<String, Object> params, final ICallback callback) {
        Request request = addHeaders().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                failedCallBack(e.toString(), callback);

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                responseCallBack(response, callback);

            }
        });
    }


    /**
     * 同步post
     */

    @Override
    public void postSyn(String url, Map<String, Object> params, ICallback callback) {
        try {
            RequestBody requestBody = appendBody(params);
            Request request = addHeaders().url(url).post(requestBody).build();
            Response response = mOkHttpClient.newCall(request).execute();
            responseCallBack(response, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 同步get
     */
    @Override
    public void getSyn(String url, Map<String, Object> params, ICallback callback) {
        try {
            Request request = addHeaders().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            responseCallBack(response, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    //==========================================内部方法============================================================

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder();
//                .addHeader("", "")
        return builder;
    }


    /**
     * 统一处理onResponse信息
     */
    private void responseCallBack(Response response, final ICallback callBack) throws IOException {
        Log.i("接口返回response：", response + "");
        final String result = response.body().string();
        if (response.isSuccessful()) {
            successCallBack(result, callBack);
        } else {
            failedCallBack(response.toString(), callBack);
        }

    }


    /**
     * 统一处理成功信息
     */
    private void successCallBack(final String result, final ICallback callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(result);
                }
            }
        });
    }

    /**
     * 统一处理失败信息
     */

    private void failedCallBack(final String errorMsg, final ICallback callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(errorMsg);
                }
            }
        });
    }


    /**
     * 获取RequestBody
     */
    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Log.i("接口请求参数：", entry.getKey() + ":" + entry.getValue().toString());
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }


}
