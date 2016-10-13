package com.medical.patient.model;

import java.util.Map;

/**
 * Created by chenjun on 2016/10/11.
 *
 * 量表
 */
public class ProTableBean {
    private int id; //量表id

    private String name;    //量表name

    private String ename;

    private String purpose;

    private String copyright;

    private String binglifenlei;

    private String jibing;  //适合疾病

    private String createdate;

    private String creator;

    private Map<Integer, TableItemBean> map;


    public String getBinglifenlei() {
        return binglifenlei;
    }

    public void setBinglifenlei(String binglifenlei) {
        this.binglifenlei = binglifenlei;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJibing() {
        return jibing;
    }

    public void setJibing(String jibing) {
        this.jibing = jibing;
    }

    public Map<Integer, TableItemBean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, TableItemBean> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
