package com.ys.config;


import com.ys.exceptions.APIException;
import com.ys.utils.APIResult;
import com.ys.utils.enumeration.APICode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by libin on 2017/4/9.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public APIResult handle(Exception e) {
        logger.error("【系统异常】{}", e);

        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            return new APIResult(exception.getApiCode());
        }else {
            return new APIResult(APICode.ERROR);
        }
    }
}
