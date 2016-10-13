package com.medical.patient.controller;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.medical.patient.R;
import com.medical.patient.app.Constants;
import com.medical.patient.app.HttpUtil;
import com.medical.patient.app.SharedPrefsUtil;
import com.medical.patient.model.AccountBean;
import com.medical.patient.view.account.activity.LoginActivity;
import com.medical.patient.widget.snackbar.CustomSnackBar;

import cz.msebera.android.httpclient.Header;

/**
 * Created by chenjun on 2016/10/8.
 */
public class AccountController {
    // 验证手机号
    public static void accountVerify(final Context context, String phone) {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("phoneNumber", phone);

        client.post(Constants.ACCOUNT_VERIFY, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(Constants.TAG, "失败 responseString----------> " + responseString + "  " + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(Constants.TAG, "成功 responseString----------> " + responseString+ "  " + statusCode);
            }
        });
    }

    // 病人注册
    public static void accountRegister(final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();

        AccountBean newAccount = new AccountBean();
        newAccount.setUserName(SharedPrefsUtil.getStringValue(context, "phone"));
        newAccount.setPassWord(SharedPrefsUtil.getStringValue(context, "password"));

        Gson gson = new Gson();
        RequestParams params = new RequestParams();
        params.put("gson", gson.toJson(newAccount));

        Log.d(Constants.TAG, "gson ----------> " + gson.toJson(newAccount));

        client.post(Constants.ACCOUNT_REGISTER, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                Log.d(Constants.TAG, "注册失败 ----------> " + statusCode + " " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(Constants.TAG, "注册成功 ----------> " + statusCode + " " + responseString);
            }
        });
    }

    // 得到病人的信息
    public static AccountBean accountInfo;
    public static void getAccountDetails(final Context context) {
        HttpUtil.getNoParams(Constants.ACCOUNT_DETAILS, new BaseJsonHttpResponseHandler<AccountBean>() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  String rawJsonData, AccountBean errorResponse) {
                Log.d(Constants.TAG, "详细信息 ---------> " + rawJsonData + "  " + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse,
                                  AccountBean response) {
                if (response != null) {
                    accountInfo = response;
                    context.sendBroadcast(new Intent("AccountDetails"));
                }
            }

            @Override
            protected AccountBean parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                Log.d(Constants.TAG, "详细信息 ---------> " + rawJsonData);
                Gson gson = new Gson();
                AccountBean accountDetails = gson.fromJson(rawJsonData, AccountBean.class);
                return accountDetails;
            }
        });
    }

    // 编辑后上传病人的信息
    public static void updateAccountDetails(final Context context, AccountBean accountInfo) {
        Gson gson = new Gson();
        RequestParams params = new RequestParams();
        params.put("gson", gson.toJson(accountInfo));
        HttpUtil.post(Constants.ACCOUNT_EDIT, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(Constants.TAG, "编辑 失败---------> " + statusCode + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(Constants.TAG, "编辑 成功---------> " + statusCode + responseString);
            }
        });
    }

    // 用户登录
    public static void accountLogin(final Context context, String name, String pwd) {
        RequestParams params = new RequestParams();
        params.add("loginName", name);
        params.add("password", pwd);

        HttpUtil.post(Constants.ACCOUNT_LOGIN, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CustomSnackBar.showNoAction(context,
                        ContextCompat.getColor(context, R.color.colorAccent),
                        "连接服务器失败", LoginActivity.instance);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(Constants.TAG, "登录 ---------> " + statusCode + responseString);
                if (responseString.equals("410")) {
                    context.sendBroadcast(new Intent(Constants.LOGIN_SUCCESS_ACTION));
                } else {
                    CustomSnackBar.showNoAction(context,
                            ContextCompat.getColor(context, R.color.colorAccent),
                            "登录失败，请重试", LoginActivity.instance);
                }
            }
        });

    }
}
