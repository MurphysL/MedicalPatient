package com.medical.patient.view.account.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.medical.patient.BaseActivity;
import com.medical.patient.R;
import com.medical.patient.controller.AccountController;
import com.medical.patient.model.AccountBean;
import com.medical.patient.widget.avatarpicker.AvatorPickerPopupWindow;
import com.medical.patient.widget.dialog.CheckBoxDialog;
import com.medical.patient.widget.dialog.DropDownListDialog;
import com.medical.patient.widget.dialog.EditTextDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountDetailsActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private Context mContext;
    public static AccountDetailsActivity instance;
    private Toolbar toolbar;
    private TextView sigout_button;
    private ImageView avatar;
    private TextView name, gender, phone, email, age, birth_date, profession, address;
    private TextView hospital, department, ruyuanDate, ruyuanNumber, zhuguanDocotr, zhuzhiDoctor, zhuguanNurser;
    private AccountBean patientDetailsInfo;

    // 判断日期点击 (1 --> 出生日期 、2 --> 住院日期)
    private int DATE_SWITCH;

    // 用户判断是否修改过信息
    private boolean isClick = false;


    /*private List<String> hospitalList = new ArrayList<>();
    private List<String> departList = new ArrayList<>();*/

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction("AccountDetails");
        registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "AccountDetails":
                    patientDetailsInfo = AccountController.accountInfo;
                    setData();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        instance = this;
        mContext = this;

        bindViews();
        initViews();


        AvatorPickerPopupWindow.createCameraTempFile(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AccountController.accountInfo == null) {
            AccountController.getAccountDetails(mContext);
        }

        setData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         // 上传数据到服务器
        if (isClick == true) {
            // 编辑数据之后  要从服务器获取新更新后的信息 要将
            AccountController.accountInfo = null;

            AccountBean account = new AccountBean();
            if (name.getText().toString().trim().equals(""))
                account.setStaffName("");
            else
                account.setStaffName(name.getText().toString().trim());

            if (gender.getText().toString().trim().equals(""))
                account.setStaffSex("");
            else
                account.setStaffSex(gender.getText().toString().trim());

            if (age.getText().toString().trim().equals(""))
                account.setAge("");
            else
                account.setAge(age.getText().toString().trim());

            if (birth_date.getText().toString().trim().equals(""))
                account.setBirthday("");
            else
                account.setBirthday(birth_date.getText().toString().trim());

            if (phone.getText().toString().trim().equals(""))
                account.setFixedphone("");
            else
                account.setFixedphone(phone.getText().toString().trim());

            if (email.getText().toString().trim().equals(""))
                account.setEmail("");
            else
                account.setEmail(email.getText().toString().trim());

            if (profession.getText().toString().trim().equals(""))
                account.setProfession("");
            else
                account.setProfession(profession.getText().toString().trim());

            if (address.getText().toString().trim().equals(""))
                account.setAddress("");
            else
                account.setAddress(address.getText().toString().trim());

            if (zhuguanDocotr.getText().toString().trim().equals(""))
                account.setZgys("");
            else
                account.setZgys(zhuguanDocotr.getText().toString().trim());

            if (zhuguanNurser.getText().toString().trim().equals(""))
                account.setZghs("");
            else
                account.setZghs(zhuguanNurser.getText().toString().trim());

            if (ruyuanNumber.getText().toString().trim().equals(""))
                account.setZyy("");
            else
                account.setZyy(ruyuanNumber.getText().toString().trim());

            if (ruyuanDate.getText().toString().trim().equals(""))
                account.setRydate("");
            else
                account.setRydate(ruyuanDate.getText().toString().trim());

            AccountController.updateAccountDetails(mContext, account);
        }
    }

    @Override
    public void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sigout_button = (TextView) findViewById(R.id.sigout_button);

        avatar = (ImageView) findViewById(R.id.account_avatar);

        name = (TextView) findViewById(R.id.account_name);
        gender = (TextView) findViewById(R.id.account_gender);
        age = (TextView) findViewById(R.id.account_age);
        birth_date = (TextView) findViewById(R.id.account_birth_date);
        phone = (TextView) findViewById(R.id.account_phone);
        email = (TextView) findViewById(R.id.account_email);
        profession = (TextView) findViewById(R.id.account_profession);
        address = (TextView) findViewById(R.id.account_address);

        hospital = (TextView) findViewById(R.id.account_hospital);
        department = (TextView) findViewById(R.id.account_ruyuan_department);
        ruyuanDate = (TextView) findViewById(R.id.account_ruyuan_date);
        ruyuanNumber = (TextView) findViewById(R.id.account_ruyuan_number);
        zhuguanDocotr = (TextView) findViewById(R.id.account_zhuguan_doctor);
        zhuzhiDoctor = (TextView) findViewById(R.id.account_zhuzhi_doctor);
        zhuguanNurser = (TextView) findViewById(R.id.account_zhuguan_nurser);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.mplib_back);

        setListeners();

        /*hospitalList.add("山西省人民医院");
        hospitalList.add("山西医科大学第一医院");
        hospitalList.add("山西中医学院中西医结合医院");
        hospitalList.add("山西医科大学附属第二医院");
        hospitalList.add("山西省心血管疾病医院");
        hospitalList.add("山西省儿童医院");
        hospitalList.add("大同市322医院");

        departList.add("康复医学科");
        departList.add("检验科");
        departList.add("脊柱外科");
        departList.add("口腔科");
        departList.add("耳鼻喉科");
        departList.add("急诊科");
        departList.add("输血科");
        departList.add("关节病研究所");
        departList.add("外科重病监护");*/
    }

    private void setData() {
        if (patientDetailsInfo != null) {
            name.setText(patientDetailsInfo.getStaffName());
            gender.setText(patientDetailsInfo.getStaffSex());
            age.setText(patientDetailsInfo.getAge());
            birth_date.setText(patientDetailsInfo.getBirthday());

            phone.setText(patientDetailsInfo.getUserName());
            email.setText(patientDetailsInfo.getEmail());
            profession.setText(patientDetailsInfo.getProfession());
            address.setText(patientDetailsInfo.getAddress());


            hospital.setText(patientDetailsInfo.getHosiptalName());
            department.setText(patientDetailsInfo.getDepartName());
            ruyuanDate.setText(patientDetailsInfo.getRydate());
            ruyuanNumber.setText(patientDetailsInfo.getZyy());
            zhuguanDocotr.setText(patientDetailsInfo.getZgys());
            zhuzhiDoctor.setText(patientDetailsInfo.getZzys());
            zhuguanNurser.setText(patientDetailsInfo.getZghs());
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year+"-"+((monthOfYear<10)? "0"+monthOfYear : monthOfYear)+"-"+
                ((dayOfMonth<10)? "0"+dayOfMonth : dayOfMonth);
        if (DATE_SWITCH == 1) {
            birth_date.setText(date);
        } else if (DATE_SWITCH == 2) {
            ruyuanDate.setText(date);
        }
    }

    @Override
    public void setListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sigout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = false;
                startActivity(new Intent(AccountDetailsActivity.this, LoginActivity.class));
                finish();
            }
        });

        findViewById(R.id.layout_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvatorPickerPopupWindow.pickerAvatorPopupWindow(instance, view);

            }
        });

        findViewById(R.id.layout_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "姓名", "请输入您的姓名",
                        "\"姓名\"在这里输入", name, InputType.TYPE_CLASS_TEXT);
            }
        });

        findViewById(R.id.layout_age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "年龄", "请输入您的年龄",
                        "\"年龄\"在这里输入", age, InputType.TYPE_CLASS_NUMBER);
            }
        });

        findViewById(R.id.layout_gender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                CheckBoxDialog.newCheckBoxDialog(mContext, "性别", gender);
            }
        });

        findViewById(R.id.layout_birth_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                dateDialog();
                DATE_SWITCH = 1;
            }
        });

        findViewById(R.id.layout_profession).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "职业", "请输入您的当前职业",
                        "\"职业\"在这里输入", profession, InputType.TYPE_CLASS_TEXT);
            }
        });

        findViewById(R.id.layout_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "住址", "请输入您的现居地",
                        "\"住址\"在这里输入", address, InputType.TYPE_CLASS_TEXT);
            }
        });

        findViewById(R.id.layout_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "电话号码", "请输入您的电话号码",
                        "\"电话号码\"在这里输入", phone, InputType.TYPE_CLASS_PHONE);
            }
        });

        findViewById(R.id.layout_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "邮箱", "请输入您的邮箱",
                        "\"邮箱\"在这里输入", email, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }
        });

        /*findViewById(R.id.layout_hospital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropDownListDialog.newListDialog(mContext, "所在医院", hospitalList, hospital);
            }
        });

        findViewById(R.id.layout_ruyuan_department).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropDownListDialog.newListDialog(mContext, "入院科室", departList, department);
            }
        });*/

        findViewById(R.id.layout_ruyuan_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                dateDialog();
                DATE_SWITCH = 2;
            }
        });

        findViewById(R.id.layout_ruyuan_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "住院号", "请输入您的住院号",
                        "\"住院号\"在这里输入", ruyuanNumber, InputType.TYPE_CLASS_TEXT);
            }
        });

        findViewById(R.id.layout_zhuguan_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "主管医生", "请输入您的主管医生姓名",
                        "\"主管医生姓名\"在这里输入", zhuguanDocotr, InputType.TYPE_CLASS_TEXT);
            }
        });

        /*
        findViewById(R.id.layout_zhuzhi_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextDialog.newEditDialog(mContext, "主治医生", "请输入您的主治医生姓名",
                        "\"主治医生姓名\"在这里输入", zhuzhiDoctor, InputType.TYPE_CLASS_TEXT);
            }
        });*/

        findViewById(R.id.layout_zhuguan_nurser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                EditTextDialog.newEditDialog(mContext, "主管护士", "请输入您的主管护士姓名",
                        "\"主管护士姓名\"在这里输入", zhuguanNurser, InputType.TYPE_CLASS_TEXT);
            }
        });
    }

    private void dateDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AccountDetailsActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case AvatorPickerPopupWindow.REQUEST_CAPTURE: // 调用系统相机返回
                if (resultCode == RESULT_OK) {
                    AvatorPickerPopupWindow.gotoClipActivity(instance, Uri.fromFile(AvatorPickerPopupWindow.tempFile));
                }
                break;
            case AvatorPickerPopupWindow.REQUEST_PICK:  // 调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    AvatorPickerPopupWindow.gotoClipActivity(instance, uri);
                }
                break;
            case AvatorPickerPopupWindow.REQUEST_CROP_PHOTO:  // 剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = AvatorPickerPopupWindow.getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (avatar != null)
                        avatar.setImageBitmap(bitMap);

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......
                    // AccountController.uploadAccountAvatar(mContext, cropImagePath);
                }
                break;
        }
    }
}
