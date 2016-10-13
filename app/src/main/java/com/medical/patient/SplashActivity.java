package com.medical.patient;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.medical.patient.message.UserBean;
import com.medical.patient.message.UserModel;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserModel.getInstance().login("y", "y", new LogInListener() {

            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    UserBean user = (UserBean) o;
                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            // 为了测试方便
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }, 2000);
                    // finish();
                } else {
                    Toast.makeText(SplashActivity.this , e.getMessage() + "(" + e.getErrorCode() + ")" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
