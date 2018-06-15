package com.fsc.newsnets.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * okhttp网络连接工具类
 */
public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    public OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10,TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30,TimeUnit.SECONDS);

        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
    }
    private synchronized static OkHttpUtils getmInstance(){
        if(mInstance == null){
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }



    //延迟请求的返回结果
    private void deliveryResult(final ResultCallback callback, final Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailCallback(callback,e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String str = request.body().toString();
                    if(callback.mType == String.class){
                        sendSuccessCallback(callback,str);
                    }else {
                        //此处缺少json解析的数据，后期补上(已处理)
                        Object obj = JsonUtils.deserialize(str,callback.mType);
                        sendSuccessCallback(callback,obj);
                    }
                } catch (final Exception e) {
                    //此处后期补上打印日志（已处理）
                    LogUtils.e(TAG,"转换json失败",e);
                    sendFailCallback(callback,e);
                }
            }
        });
    }

    //发送失败返回
    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    //发送成功返回
    private void sendSuccessCallback(final ResultCallback callback, final Object obj){
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if(callback != null){
                    callback.onSuccess(obj);
                }
            }
        });
    }


    /**
     * 创建post请求
     */
    private Request buildPostRequest(String url, List<Param> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    /**
     * post请求参数类
     */
    public static class Param{
        String key;
        String value;

        public Param(){

        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    //get请求
    private void getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        deliveryResult(callback,request);
    }

    //post请求
    private void postRequest(String url, final ResultCallback callback, List<Param> params) {
        Request request = buildPostRequest(url,params);
        deliveryResult(callback,request);
    }
    /**********************************对外接口***********************************/
    /**
     * get请求
     */
    public static void get(String url,ResultCallback callback){
        getmInstance().getRequest(url,callback);
    }

    /**
     * post请求
     */
    public static void post(String url, final ResultCallback callback, List<Param> params) {
        getmInstance().postRequest(url,callback,params);
    }


    /**
     * http请求回调类,回调方法在UI线程中执行
     */
    public static abstract class ResultCallback<T>{
        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass){
            Type superclass = subclass.getGenericSuperclass();
            if(superclass instanceof  Class){
                throw new RuntimeException("Missing type parameter");
            }
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }

        //请求成功回调
        public abstract void onSuccess(T response);

        //请求失败回调
        public abstract void onFailure(Exception e);
    }
}
