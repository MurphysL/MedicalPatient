package com.medical.patient.message;



import com.medical.patient.db.NewFriend;

import cn.bmob.v3.BmobUser;

/**
 * UserBean
 *
 * 用户数据
 *
 * @author: lenovo
 * @time: 2016/7/13 21:02
 */

public class UserBean extends BmobUser {

    private String avatar;
    private String id;

    public UserBean(){}

    public UserBean(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
