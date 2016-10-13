package com.medical.patient.view.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.medical.patient.R;

/**
 * 患者就诊信息
 */
public class PatientJiuzhenInfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_jiuzhen_info);

        bindViews();
        initViews();
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        next_btn = (TextView) findViewById(R.id.next_btn);
    }

    private void initViews() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.mplib_back);

        setListeners();
    }

    private void setListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientJiuzhenInfoActivity.this, SurveyTableActivity.class));
            }
        });
    }
}
