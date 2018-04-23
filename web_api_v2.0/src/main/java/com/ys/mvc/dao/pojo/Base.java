package com.ys.mvc.dao.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by peijian.wang on 2017/3/16 0016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Base implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
