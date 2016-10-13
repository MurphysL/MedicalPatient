package com.medical.patient.app;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;


public class HttpUtil {
    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        // 每次请求都要带上cookie
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getAppContext());
        HttpUtil.client.setCookieStore(myCookieStore);

        client.get(url, params, responseHandler);
    }

    public static void getNoParams(String url, AsyncHttpResponseHandler responseHandler) {
        // 每次请求都要带上cookie
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getAppContext());
        HttpUtil.client.setCookieStore(myCookieStore);

        client.get(url, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        // 每次请求都要带上cookie
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getAppContext());
        HttpUtil.client.setCookieStore(myCookieStore);

        client.post(url, params, responseHandler);
    }

    public static void postNoParams(String url, AsyncHttpResponseHandler responseHandler) {
        // 每次请求都要带上cookie
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getAppContext());
        HttpUtil.client.setCookieStore(myCookieStore);

        client.post(url, responseHandler);
    }

}
