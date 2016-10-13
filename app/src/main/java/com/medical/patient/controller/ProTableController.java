package com.medical.patient.controller;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.medical.patient.R;
import com.medical.patient.app.Constants;
import com.medical.patient.app.HttpUtil;
import com.medical.patient.model.ProTableBean;
import com.medical.patient.model.TableItemBean;
import com.medical.patient.view.home.activity.ProFillinActivity;
import com.medical.patient.view.home.activity.SurveyTableActivity;
import com.medical.patient.widget.snackbar.CustomSnackBar;

import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by chenjun on 2016/10/11.
 */
public class ProTableController {
    public static ProTableBean proTable;
    public static void getProTables(final Context context, String patient, String disease) {
        RequestParams params = new RequestParams();
        params.put("meshType", disease);

        HttpUtil.post(Constants.GET_PRO_TABLES, params, new BaseJsonHttpResponseHandler<ProTableBean>() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  String rawJsonData, ProTableBean errorResponse) {
                CustomSnackBar.showNoAction(context,
                        ContextCompat.getColor(context, R.color.colorAccent),
                        "未连接到服务器", ProFillinActivity.instance);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse,
                                  ProTableBean response) {
                if (response != null) {
                    proTable = null;
                    ProFillinActivity.instance.getProTablesSuccess();
                    proTable = response;
                } else {
                    CustomSnackBar.showNoAction(context,
                            ContextCompat.getColor(context, R.color.colorAccent),
                            "获取量表失败，请重试", ProFillinActivity.instance);
                }
            }

            @Override
            protected ProTableBean parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                Gson gson = new Gson();
                ProTableBean proTable = gson.fromJson(rawJsonData, ProTableBean.class);
                return proTable;
            }
        });
    }
}
