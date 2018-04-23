package utils.tools;

/**
 * Created by peijian.wang on 2017/3/17.
 */
public class Checker {

    public static boolean isNull(String str){

        if(str == null){
           return true;
        }else if("".equals(str)){
            return true;
        }

        return false;
    }

}
