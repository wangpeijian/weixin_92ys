package mvc.dao.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by peijian.wang on 2017/3/16 0016.
 */
public class Base implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
