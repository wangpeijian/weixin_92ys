package com.ys.utils;

import com.alibaba.fastjson.JSON;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.utils.enumeration.APICode;

/**
 * Created by peijian.wang on 2017/3/14.
 */
public class APIResult {

    public APIResult(){
        this.code = APICode.SUCCESS.getCode();
        this.msg = APICode.SUCCESS.getMsg();
    }

    public APIResult(APICode apiCode) {
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
    }

    public APIResult(Object data) {
        this.code = APICode.SUCCESS.getCode();
        this.msg = APICode.SUCCESS.getMsg();
        this.data = data;
    }

    public APIResult(APICode apiCode, Object data) {
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
        this.data = data;
    }

    public APIResult(APICode apiCode, Object data, Pageable page) {
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
        this.data = data;
        this.page = page;
    }

    private int code;

    private String msg;

    private Object data;

    private Pageable page;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public APIResult setCodeAndMsg(APICode apiCode){
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
