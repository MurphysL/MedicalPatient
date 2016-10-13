package com.medical.patient.message.i;

import com.medical.patient.message.UserBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.BmobListener;

public abstract class QueryUserListener extends BmobListener<UserBean> {

    public abstract void done(UserBean s, BmobException e);

    @Override
    protected void postDone(UserBean o, BmobException e) {
        done(o, e);
    }
}
