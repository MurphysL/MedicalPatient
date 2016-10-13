package com.medical.patient.view.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medical.patient.R;
import com.medical.patient.controller.ProTableController;
import com.medical.patient.widget.dialog.DropDownListDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 量表填写
 */
public class ProFillinActivity extends AppCompatActivity {
    private Context mContext;
    private Toolbar toolbar;
    private TextView text_guide;
    private TextView next_btn;

    public static ProFillinActivity instance;

    private RelativeLayout rl_patient_type, rl_disease_type;
    private TextView patient_type, disease_type;
    private List<String> patientType = new ArrayList<>();
    private List<String> diseaseType = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_fillin);

        instance = this;
        mContext = this;

        // 患者类型
        patientType.add("住院患者");
        patientType.add("随访患者");

        // 疾病类型
        diseaseType.add("脑卒中");

        bindViews();
        initViews();
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        text_guide = (TextView) findViewById(R.id.text_guide);

        rl_patient_type = (RelativeLayout) findViewById(R.id.layout_patient_type);
        rl_disease_type = (RelativeLayout) findViewById(R.id.layout_disease_type);

        patient_type = (TextView) findViewById(R.id.patient_type);
        disease_type = (TextView) findViewById(R.id.disease_type);

        next_btn = (TextView) findViewById(R.id.next_btn);
    }

    private void initViews() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.mplib_back);

        text_guide.setText(getString(R.string.pro_guide));

        setListeners();
    }

    private void setListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rl_patient_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropDownListDialog.newListDialog(mContext, "患者类型", patientType, patient_type);
            }
        });

        rl_disease_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropDownListDialog.newListDialog(mContext, "疾病类型", diseaseType, disease_type);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProTableController.getProTables(mContext, patient_type.getText().toString().trim(),
                        disease_type.getText().toString().trim());
            }
        });
    }

    public void getProTablesSuccess() {
        startActivity(new Intent(ProFillinActivity.this, PatientJiuzhenInfoActivity.class));
    }
}
