package com.yulu.zhaoxinpeng.mytreasuremap.net;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/3/29.
 */
// 网络的客户端类
//封装 OkHttpClient
public class NetClient {

    private static NetClient mNetClient;
    private OkHttpClient mOkHttpClient;
    private String BaseUrl="http://admin.syfeicuiedu.com";

    public NetClient() {

        //创建一个日志拦截器
        HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor();

        //设置打印日志的级别 （Body 意为全部）
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okHttpClient 的单例化
        mOkHttpClient = new OkHttpClient.Builder().addInterceptor(mInterceptor).build();
    }

    public static NetClient getInstance(){
        if (mNetClient==null) {
            mNetClient = new NetClient();
        }
        return mNetClient;
    }

    // 将每一个请求都单独的放置到一个方法里面
    public Call getData(){
        // 构建请求
        final Request request = new Request.Builder()
                .get()// 请求的方式
                .url("http://www.baidu.com")// 请求的地址
                .addHeader("content-type","html")// 添加请求头信息
                .addHeader("context-length","1024")
                // Get请求不需要添加请求体
                .build();
        // 根据请求进行建模Call
        return mOkHttpClient.newCall(request);
    }

    //Post 形式的请求构建
    public Call postData(){

        /**
         * 1. 当需要上传的数据是键值对的形式的时候
         *  username = “”；
         *  password = “”；
         *  json = “{username=“”，password = “”}”
         *  一般以表单的形式进行提交
         *
         * 2. 当上传的数据是多个部分的时候
         *  多部分提交
         */
        // 表单形式请求体的构建
//        RequestBody formBody = new FormBody.Builder()
//                .add("username","123456")
//                .add("password","123456")
//                .build();
//
//        // 多部分请求体的构建
//        RequestBody multBody = new MultipartBody.Builder()
//                .addFormDataPart("photo","abc.png",RequestBody.create(null,"abc.png"))
//                .addFormDataPart("name","123456")
//                .build();

        // 需要上传的请求体:字符串、文件、数组等
        RequestBody requestbody=RequestBody.create(null,"{\n" +
                "\"UserName\":\"qjd\",\n" +
                "\"Password\":\"654321\"\n" +
                "}\n");
        Request request=new Request.Builder()
                .post(requestbody)
                .url(BaseUrl+"/Handler/UserHandler.ashx?action=login")
                .build();

        return mOkHttpClient.newCall(request);
    }
}
