package com.medical.patient.view.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.medical.patient.BaseActivity;
import com.medical.patient.R;

public class RegisterStepTwoActivity extends BaseActivity {
    private TextView register_step_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_two);

        bindViews();
        initViews();
    }

    @Override
    public void bindViews() {
        register_step_two = (TextView) findViewById(R.id.register_step_two);
    }

    @Override
    public void initViews() {
        setListeners();
    }

    @Override
    public void setListeners() {
        register_step_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterStepTwoActivity.this, RegisterStepThreeActivity.class));
            }
        });
    }
}
