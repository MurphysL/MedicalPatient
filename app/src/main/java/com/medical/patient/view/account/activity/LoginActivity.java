package com.medical.patient.view.account.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.medical.patient.BaseActivity;
import com.medical.patient.MainActivity;
import com.medical.patient.R;
import com.medical.patient.app.Constants;
import com.medical.patient.app.SharedPrefsUtil;
import com.medical.patient.controller.AccountController;
import com.medical.patient.widget.snackbar.CustomSnackBar;

public class LoginActivity extends BaseActivity {
    private Context mContext;
    private Toolbar toolbar;
    public static LoginActivity instance;
    private TextView register_btn, login_btn;
    private ImageView login_phone_delete;
    private EditText login_phone, login_password;

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.LOGIN_SUCCESS_ACTION);
        registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Constants.LOGIN_SUCCESS_ACTION:
                    SharedPrefsUtil.putStringValue(mContext, "phone", login_phone.getText().toString().trim());
                    SharedPrefsUtil.putStringValue(mContext, "password", login_password.getText().toString().trim());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        instance = this;

        bindViews();
        initViews();
    }

    @Override
    public void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        login_phone_delete = (ImageView) findViewById(R.id.login_phone_delete);

        login_phone = (EditText) findViewById(R.id.login_phone);
        login_password = (EditText) findViewById(R.id.login_password);

        register_btn = (TextView) findViewById(R.id.pager_register);
        login_btn = (TextView) findViewById(R.id.login_button);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("");

        login_phone.setText(SharedPrefsUtil.getStringValue(mContext, "phone"));
        login_password.setText(SharedPrefsUtil.getStringValue(mContext, "password"));

        setListeners();
    }

    @Override
    public void setListeners() {
        login_phone_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_phone.setText("");
                login_password.setText("");
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterStepOneActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login_phone.getText().toString().trim().equals("") &&
                        !login_password.getText().toString().trim().equals("")) {
                    AccountController.accountLogin(getApplicationContext(),
                            login_phone.getText().toString().trim(),
                            login_password.getText().toString().trim());
                } else {
                    CustomSnackBar.showNoAction(mContext,
                            ContextCompat.getColor(mContext, R.color.colorAccent),
                            "密码或用户名错误", LoginActivity.this);
                }
            }
        });
    }
}
