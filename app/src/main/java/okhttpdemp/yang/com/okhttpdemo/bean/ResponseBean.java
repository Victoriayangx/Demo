package okhttpdemp.yang.com.okhttpdemo.bean;

import java.io.Serializable;

/**
 * Created by yang on 2017/7/3.
 */

public class ResponseBean<T> implements Serializable{
    public String status;
    public String error;
    public T msg;
}
