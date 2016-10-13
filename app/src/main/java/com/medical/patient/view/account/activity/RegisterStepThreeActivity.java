package com.medical.patient.view.account.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.medical.patient.BaseActivity;
import com.medical.patient.R;
import com.medical.patient.app.SharedPrefsUtil;
import com.medical.patient.controller.AccountController;

public class RegisterStepThreeActivity extends BaseActivity {
    private Context mContext;
    private TextView register_step_three_pwd;
    private TextView register_step_three_pwd_repeat;
    private TextView register_step_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_three);

        mContext = this;
        bindViews();
        initViews();
    }

    @Override
    public void bindViews() {
        register_step_three_pwd = (TextView) findViewById(R.id.register_step_three_pwd);
        register_step_three_pwd_repeat = (TextView) findViewById(R.id.register_step_three_pwd_repeat);
        register_step_three = (TextView) findViewById(R.id.register_step_three);
    }

    @Override
    public void initViews() {
        setListeners();
    }

    @Override
    public void setListeners() {
        register_step_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsUtil.putStringValue(mContext, "password",
                        register_step_three_pwd.getText().toString().trim());

                AccountController.accountRegister(mContext);
            }
        });
    }
}
