package com.example.p2p.utils;


import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by chenyuelun on 2017/6/21.
 */

public class HttpUtils {

    private static HttpUtils httpUtils = new HttpUtils();

    private HttpUtils(){}

    public static HttpUtils getInstance(){
        return httpUtils;
    }

    public void get(String url, OnHttpClientListener onHttpClientListener){
        this.onHttpClientListener = onHttpClientListener;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url,new MyAsyncHttpResponseHandler());
    }

    private class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
        @Override
        public void onSuccess(int statusCode, String content) {
            super.onSuccess(statusCode, content);
            if(onHttpClientListener != null) {
                onHttpClientListener.onSuccess(statusCode,content);
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);
            if(onHttpClientListener != null) {
                onHttpClientListener.onFailure(error,content);
            }
        }
    }



    private void prosessData(int statusCode, String content) {


    }

   private OnHttpClientListener onHttpClientListener;
    public interface OnHttpClientListener{
        void onSuccess(int statusCode, String content);
        void onFailure(Throwable error, String content);
    }


}
