package com.medical.patient.view.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.medical.patient.BaseActivity;
import com.medical.patient.R;
import com.medical.patient.app.SharedPrefsUtil;
import com.medical.patient.controller.AccountController;

public class RegisterStepOneActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView register_step_one;
    private TextView register_phone;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_one);

        mContext = this;

        bindViews();
        initViews();
    }

    @Override
    public void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        register_step_one = (TextView) findViewById(R.id.register_step_one);
        register_phone = (TextView) findViewById(R.id.register_phone);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.mplib_back);
        setListeners();
    }

    @Override
    public void setListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register_step_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 将电话号码保存到SharedPreferences
                SharedPrefsUtil.putStringValue(mContext, "phone", register_phone.getText().toString().trim());
                AccountController.accountVerify(mContext, register_phone.getText().toString().trim());
                startActivity(new Intent(RegisterStepOneActivity.this, RegisterStepTwoActivity.class));
            }
        });
    }
}
