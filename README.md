1、在Application里面对框架进行初始化：

    HttpHelper.init(new OkhttpProcessor());



2、具体代码使用：

因为返回的格式是统一格式，所以用泛型替代。如果返回的msg是object类，那在下面标注红色的地方，直接为ResponseBean<bean类名称>，如果返回msg为数组，则泛型为ResponseBean<List<bean类名称>。



    private Map<String,Object> httpParams = new HashMap<>();  
        public void getlistData(){  
            httpParams.put("a", "aa");  
            httpParams.put("b","bb");  
            httpParams.put("c","cc");  
            HttpHelper.obtain().post(HttpUrlUtil.GET_PERFORMANCE_FM, httpParams, new HttpCallback<ResponseBean<List<User>>>() {
                @Override  
                public void onSuccess(ResponseBean responseBean) {  
                    //处理数据  
                }  
      
                @Override  
                public void onFailure(String e) {  
                    //处理数据  
                }  
            });  
        }  