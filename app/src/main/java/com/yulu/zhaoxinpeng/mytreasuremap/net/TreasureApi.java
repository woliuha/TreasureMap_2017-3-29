package com.yulu.zhaoxinpeng.mytreasuremap.net;

/**
 * Created by Administrator on 2017/3/30.
 */

import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.MultiUser;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.User;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.account.Update;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.account.UpdateResult;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.account.UploadResult;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.login.LoginResult;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.register.RegisterResult;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.Area;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.Treasure;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.detail.TreasureDetail;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.detail.TreasureDetailResult;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.hide.HideTreasure;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.hide.HideTreasureResult;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Retrofit框架中的对应服务器接口
 */
public interface TreasureApi {

    //测试构建请求
    @GET("http://www.baidu.com")
    @Headers({"context-length:1024"})
    Call<ResponseBody> getData();


    //Retrofit 登录的构建（Post方式）
    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

    // Retrofit 注册的请求
    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);

    //获取区域内的宝藏数据的请求
    @POST("/Handler/TreasureHandler.ashx?action=show")
    Call<List<Treasure>> getTreasureInArea(@Body Area area);

    //获取宝藏数据详情的请求
    @POST("/Handler/TreasureHandler.ashx?action=tdetails")
    Call<List<TreasureDetailResult>> getTreasureDetail(@Body TreasureDetail treasureDetail);

    //埋藏宝藏的请求
    @POST("/Handler/TreasureHandler.ashx?action=hide")
    Call<HideTreasureResult> hideTreasure(@Body HideTreasure hideTreasure);

    // 头像的上传
    @Multipart
    @POST("/Handler/UserLoadPicHandler1.ashx")
    Call<UploadResult> upload(@Part MultipartBody.Part part);

    // 用户头像的更新
    @POST("/Handler/UserHandler.ashx?action=update")
    Call<UpdateResult> update(@Body Update update);


    /**
     * 注解：
     * 1. 请求的方式：@GET、@POST、@PUT等
     * 2. 请求的url的处理：
     *      1. 可替换块：{id}替换的内容，@Path注解来替换。
     *      2. 查询参数：@Query("查询的参数的key")
     *                  参数比较多：@QueryMap
     * 3. 请求头信息： 添加： @Headers({}) 请求方法上面
     *                修改： @Header("x-type")String header 参数动态修改的
     * 4. 请求体：@Body String json;
     *           1. 表单：@FormUrlEncoded
     *                   @Field("username") String username
     *                   @FieldMap Map<String,String> map
     *           2. 多部分：@Multipart
     *                   @Part("name")String name
     *                   @PartMap Map<String,String> map
     */

   /* // 表单形式的请求：
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
    @FormUrlEncoded
    Call<ResponseBody> getFormData(@Field("username")String name,
                                   @Field("password")String password);

    // 多部分形式的请求：
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update")
    @Multipart
    Call<ResponseBody> getMultData(@Part("user")MultiUser user);


    @POST("group/{id}/users?sort=desc")
    @Headers({"x-type:123","x-length:1024"})
    Call<ResponseBody> groupList(@Path("id") int groupId,
                                 @Query("sort") String sort,
                                 @QueryMap Map<String,String> map,
                                 @Header("x-type")String header,
                                 @Body String json);

    @POST("xxxx")
    @FormUrlEncoded
    Call<ResponseBody> getFormUrl(@Field("username") String username,
                                  @Field("password")String password,
                                  @FieldMap Map<String,String> map);

    @POST("yyyy")
    @Multipart
    Call<ResponseBody> getMultUrl(@Part("photo")File file,
                                  @Part("name")String name,
                                  @PartMap Map<String,String> map);*/
}
