package com.medical.patient.view.home.fragment.MyDocter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.medical.patient.R;


public class AddDoctorFragment extends Fragment {
    private static final String TAG="AddDoctorFragment";
    private View view;
    private Toolbar toolbar;
    private Spinner ssyy,ssks;
    private EditText doctorName;
    private ListView doctorList;
    private Button btnCommit;

    private String hospital,deartment,doctor;
    private SimpleAdapter simpleAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_add_doctor,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        initView();
    }

    private void bindView(){
        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        ssyy = (Spinner) view.findViewById(R.id.add_doc_spinner_ssyy);
        ssks= (Spinner) view.findViewById(R.id.add_doc_spinner_ssks);
        doctorName= (EditText) view.findViewById(R.id.add_doc_edit_txt_doctor_name);
        doctorList= (ListView) view.findViewById(R.id.add_doc_list);
        btnCommit= (Button) view.findViewById(R.id.btn_commit);
    }

    private void initView(){
        toolbar.setTitle("添加医生");

        //获取医院
        ssyy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*String[] hospitals=getResources().getStringArray(R.array.hospitalList);
                hospital=hospitals[i].toString();*/
            }
        });

        //获取科室
        ssks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*String[] deartments=getResources().getStringArray(R.array.department);
                deartment=deartments[i].toString();

                Toast.makeText(getContext(),"您选择了"+hospital+"的"+deartment,Toast.LENGTH_SHORT).show();*/
            }
        });

        //获取医生姓名
        doctor=doctorName.getText().toString();

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"提交");
                Log.i(TAG,"医院："+hospital+"科室"+deartment+"医生："+doctor);
            }
        });

        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyDoctorActivity activity= (MyDoctorActivity) getActivity();
                activity.changePage(adapterView.getItemAtPosition(i));
            }
        });
    }


}
