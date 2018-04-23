package com.ys.utils.tools;

import java.util.UUID;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class Lang {

    public static String getUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
