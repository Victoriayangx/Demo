package okhttpdemp.yang.com.okhttpdemo.httpUtils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2017/7/3.
 */

public class HttpHelper implements IHttpProcessor {
    private static IHttpProcessor mIHttpProcessor = null;
    private Map<String, Object> mParams;
    private static HttpHelper _instance;

    private HttpHelper() {
        mParams = new HashMap<>();

    }

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (_instance == null) {
                _instance = new HttpHelper();
            }
        }
        return _instance;
    }

    public static void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        mIHttpProcessor.post(url, params, callback);

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl = appendParams(url, params);
        mIHttpProcessor.get(finalUrl, params, callback);
    }


    @Override
    public void postSyn(String url, Map<String, Object> params, ICallback callback) {
        mIHttpProcessor.post(url, params, callback);
    }

    @Override
    public void getSyn(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl = appendParams(url, params);
        mIHttpProcessor.get(finalUrl, params, callback);
    }















    //------------无论替换哪个框架get请求都是参数拼接，因此放在这里不再在各个Processor中存放--------------------------//

    /**
     * 参数拼接
     */
    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
            urlBuilder.append("&");
        }

        String s = urlBuilder.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    //URI不允许有空格等字符，如果参数值有空格，需要此方法转换
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            //针对不支持的编码时报错，utf-8应该是支持的
            Log.e("参数转码异常", e.toString());
            throw new RuntimeException(e);
        }

    }
}
