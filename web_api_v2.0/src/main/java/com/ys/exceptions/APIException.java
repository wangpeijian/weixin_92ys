package com.ys.exceptions;

import com.ys.utils.enumeration.APICode;

/**
 * Created by wangpeijain on 2017/6/21 0021.
 */
public class APIException extends RuntimeException{

    private APICode apiCode;

    public APIException(APICode apiCode) {
        super(apiCode.getMsg());
        this.apiCode = apiCode;
    }

    public APICode getApiCode() {
        return apiCode;
    }

    public void setApiCode(APICode apiCode) {
        this.apiCode = apiCode;
    }
}