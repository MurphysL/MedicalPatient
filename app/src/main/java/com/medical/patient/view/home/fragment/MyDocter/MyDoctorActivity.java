package com.medical.patient.view.home.fragment.MyDocter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.medical.patient.R;

import java.util.ArrayList;
import java.util.List;

public class MyDoctorActivity extends AppCompatActivity {
    private static final String TAG="MyDoctorActivity";

    private Context mContext;
    private FrameLayout myDoctorFramLayout;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor);
        mContext=this;

        if (fragmentList == null)
            fragmentList = new ArrayList<>();

        bindView();
        initView();
        setDefaulFragment();
    }

    private void setDefaulFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        AddDoctorFragment addDoctorFragment=new AddDoctorFragment();
        transaction.replace(R.id.my_doc_framelayout,addDoctorFragment).commit();
    }
    private void bindView(){
        myDoctorFramLayout= (FrameLayout) findViewById(R.id.my_doc_framelayout);
    }

    private void initView(){
        AddDoctorFragment addDoctorFragment=new AddDoctorFragment();
        DoctorInfoFragment doctorInfoFragment=new DoctorInfoFragment();
        fragmentList.add(0,addDoctorFragment);
        fragmentList.add(1,doctorInfoFragment);
    }

    /**
     * 由fragment 调用，用于切换fragment
     * object == null  DoctorInfo 2 AddDoctor
     * object != null  addDoctor2DoctorInfo
     * @param object
     */
    public void changePage(Object object){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

       if(null!=object){
           Bundle doctor=new Bundle();
           doctor.putString("doctorinfo","应该是医生信息");
           fragmentList.get(1).setArguments(doctor);
           transaction.replace(R.id.my_doc_framelayout,fragmentList.get(1)).commit();
           Log.i(TAG,"addDoctor2DoctorInfo and doctor info "+object.toString());
       } else {
           transaction.replace(R.id.my_doc_framelayout,fragmentList.get(0)).commit();
           Log.i(TAG,"object==null||DoctorInfo2AddDoctor");
       }
    }









}
