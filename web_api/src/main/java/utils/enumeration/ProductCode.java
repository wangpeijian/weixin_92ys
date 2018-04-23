package utils.enumeration;

/**
 * Created by peijian.wang on 2017/4/2.
 */
public enum ProductCode {

    Service("service", "企业服务"),
    Resource("resource", "创意分享"),
    Personal("personal", "私人服务");

    // 成员变量
    private String type;
    private String name;
    // 构造方法
    ProductCode(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.type+"_"+this.name;
    }
}
