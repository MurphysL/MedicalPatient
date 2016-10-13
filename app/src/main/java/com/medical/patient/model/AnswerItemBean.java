package com.medical.patient.model;

/**
 * Created by chenjun on 2016/10/11.
 *
 * 量表题目中的单一选项
 */
public class AnswerItemBean {
    private int id;

    private String xuanxiang;

    private double jifen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getJifen() {
        return jifen;
    }

    public void setJifen(double jifen) {
        this.jifen = jifen;
    }

    public String getXuanxiang() {
        return xuanxiang;
    }

    public void setXuanxiang(String xuanxiang) {
        this.xuanxiang = xuanxiang;
    }
}
