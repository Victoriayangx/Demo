package okhttpdemp.yang.com.okhttpdemo.httpUtils;


import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yang on 2017/7/3.
 */

public abstract  class HttpCallback<Result> implements ICallback{

    @Override
    public void onSuccess(String result) {
        Log.i("返回result",result.toString());
        Gson gson = new Gson();
        try{
            Result objResult = (Result)gson.fromJson(result,analysisClassInfo(this));
            onSuccess(objResult);
        }catch (Exception e){
            Log.e("返回数据Gson解析异常",e.toString());
            onFailure(e.toString());
        }
    }


    @Override
    public void onFailure(String e) {
        Log.e("请求onFailure==",e.toString());
        onFailure(e);

    }

    public abstract void onSuccess(Result result);

    public static Type analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        return  params[0];
    }


}
