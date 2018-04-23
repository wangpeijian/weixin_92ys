package utils.tools;

import java.util.UUID;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class Lang {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
