package utils.enumeration;

/**
 * Created by peijian.wang on 2017/3/14.
 */
public enum APICode {
    SUCCESS(0, "ok"),


    USER_NOT_EXIST(10001, "用户不存在"),
    USER_PASSWORD_ERROR(10002, "密码错误"),
    USER_AUTHORITY_ERROR(10003, "权限不足"),
    USER_ALREADY_EXIST(10004, "账号已存在"),


    ERROR(-1, "error");

    // 成员变量
    private int code;
    private String msg;
    // 构造方法
    APICode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.code+"_"+this.msg;
    }
}
