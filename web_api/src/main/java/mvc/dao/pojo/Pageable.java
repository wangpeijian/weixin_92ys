package mvc.dao.pojo;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/14.
 */
public class Pageable<T> {

    private int count;

    private int current;

    private int paging = 10;

    private int start = 0;

    private List<T> List;

    private T object;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public java.util.List<T> getList() {
        return List;
    }

    public void setList(java.util.List<T> list) {
        List = list;
    }

    public void countStart(){
        this.start = (this.current - 1) * this.paging;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
