package com.medical.patient.model;


import java.util.List;
import java.util.Map;

/**
 * Created by chenjun on 2016/10/11.
 *
 * 量表中的一个题目
 */
public class TableItemBean {
    private int id;                     //id

    private String name;                //条目名称

    private int xuhao;                  //条目序号

    private String jifenfangxiang;      //计分方向

    private List<AnswerItemBean> list;      //包含的选项list

    private Map<String, String> paramMap;   //该条目的参数


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJifenfangxiang() {
        return jifenfangxiang;
    }

    public void setJifenfangxiang(String jifenfangxiang) {
        this.jifenfangxiang = jifenfangxiang;
    }

    public List<AnswerItemBean> getList() {
        return list;
    }

    public void setList(List<AnswerItemBean> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public int getXuhao() {
        return xuhao;
    }

    public void setXuhao(int xuhao) {
        this.xuhao = xuhao;
    }
}
