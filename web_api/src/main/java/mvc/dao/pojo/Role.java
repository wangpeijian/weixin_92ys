package mvc.dao.pojo;

/**
 * Created by peijian.wang on 2017/3/16 0016.
 */
public class Role extends Base {

    private String id;

    private String tag;

    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
