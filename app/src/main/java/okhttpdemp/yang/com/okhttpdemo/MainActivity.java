 package okhttpdemp.yang.com.okhttpdemo;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.widget.Button;
 import android.widget.TextView;

 import com.google.gson.Gson;

 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 import okhttpdemp.yang.com.okhttpdemo.bean.ResponseBean;
 import okhttpdemp.yang.com.okhttpdemo.bean.User;
 import okhttpdemp.yang.com.okhttpdemo.httpUtils.HttpCallback;
 import okhttpdemp.yang.com.okhttpdemo.httpUtils.HttpHelper;

 /**
  * 在baseUrl放入请求连接，httpParams放入请求参数即可
  */
 public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     private Map<String,Object> httpParams = new HashMap<>();
     private String baseUrl = "";
     private Button mButton_a;
     private Button mButton_b;
     private Button mButton_c;
     private Button mButton_d,mButton_f;
     private TextView mTv_des;
     private Gson gson;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mButton_a = (Button) findViewById(R.id.button_a);
         mButton_b = (Button) findViewById(R.id.button_b);
         mButton_c = (Button) findViewById(R.id.button_c);
         mButton_d = (Button) findViewById(R.id.button_d);
         mButton_f = (Button) findViewById(R.id.button_f);
         mTv_des = (TextView) findViewById(R.id.tv_des);

         mButton_a.setOnClickListener(this);
         mButton_b.setOnClickListener(this);
         mButton_c.setOnClickListener(this);
         mButton_d.setOnClickListener(this);
         mButton_f.setOnClickListener(this);


         httpParams.clear();
         httpParams.put("","");
         httpParams.put("","");
         httpParams.put("","");

         gson = new Gson();
     }


     /**
      * 异步post
      */
    private void click1(){
        HttpHelper.obtain().post(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
            @Override
            public void onSuccess(ResponseBean responseBean) {
                showDes(gson.toJson(responseBean));
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
         HttpHelper.obtain().get(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
                 showDes(gson.toJson(responseBean));
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
         HttpHelper.obtain().postSyn(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
                 showDes(gson.toJson(responseBean));
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
         HttpHelper.obtain().getSyn(baseUrl, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
             @Override
             public void onSuccess(ResponseBean responseBean) {
                 showDes(gson.toJson(responseBean));
             }

             @Override
             public void onFailure(String e) {

             }
         });

     }

     @Override
     public void onClick(View v) {
         switch (v.getId()){
             case R.id.button_a:
                 click1();
                 break;
             case R.id.button_b:
                 click2();
                 break;
             case R.id.button_c:
                 click3();
                 break;
             case R.id.button_d:
                 click4();
                 break;
             case R.id.button_f:
                 mTv_des.setText("");
                 break;
         }

     }


     private void showDes(final String des){
         runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 mTv_des.setText(des);
             }
         });

     }
 }
