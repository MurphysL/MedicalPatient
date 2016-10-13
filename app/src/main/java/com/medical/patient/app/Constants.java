package com.medical.patient.app;

/**
 * Created by chenjun on 2016/10/8.
 */
public class Constants {
    public static String TAG = "PatientLog";

    public static final String[] BRAODCAST = new String[] {"App的home页展示", "aaaaaaaaaaaaaaa",
            "医疗患者端test", "一月又一月一样一样犹犹豫豫也"};

    private static String BASE_URL = "http://10.0.119.84:8080/pp/";
    public static String ACCOUNT_REGISTER = BASE_URL + "register/savePatientReg";
    public static String ACCOUNT_LOGIN = BASE_URL + "login/login_action";
    public static String ACCOUNT_VERIFY = BASE_URL + "register/getphone";

    public static String ACCOUNT_DETAILS = BASE_URL + "interfaces/patient/getPatientInfo";
    public static String ACCOUNT_EDIT = BASE_URL + "interfaces/patient/savePatientInfo";

    public static String GET_PRO_TABLES = BASE_URL + "interfaces/patient/getLB";




    // action
    public static final String LOGIN_SUCCESS_ACTION = "com.patient.action.login_success";
}
