 package okhttpdemp.yang.com.okhttpdemo;

 import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttpdemp.yang.com.okhttpdemo.bean.ResponseBean;
import okhttpdemp.yang.com.okhttpdemo.bean.User;
import okhttpdemp.yang.com.okhttpdemo.httpUtils.HttpCallback;
import okhttpdemp.yang.com.okhttpdemo.httpUtils.HttpHelper;


 public class MainActivity extends AppCompatActivity {
     private Map<String,Object> httpParams = new HashMap<>();
     private String baseUrl = "https://www.alibabacloud.com/help/zh/doc-detail/31817.htm";
     private String key = "spm";
     private String value = "a3c0i.o32172zh.b99.2.f1wcDn";

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


     /**
      * 异步post
      */
    private void click1(){
        httpParams.clear();
        httpParams.put(key,value);
        HttpHelper.obtain().post(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
            @Override
            public void onSuccess(ResponseBean responseBean) {
            }

            @Override
            public void onFailure(String e) {

            }
        });


    }

     /**
      * 异步get
      */
     private void click2(){
         httpParams.clear();
         httpParams.put(key,value);
         HttpHelper.obtain().get(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
             }

             @Override
             public void onFailure(String e) {

             }
         });

     }


     /**
      * 同步post
      */
     private void click3(){
         httpParams.clear();
         httpParams.put(key,value);
         HttpHelper.obtain().postSyn(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
             }

             @Override
             public void onFailure(String e) {

             }
         });

     }


     /**
      * 同步get
      */
     private void click4(){
         httpParams.clear();
         httpParams.put(key,value);
         HttpHelper.obtain().getSyn(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
             }

             @Override
             public void onFailure(String e) {

             }
         });

     }

 }
