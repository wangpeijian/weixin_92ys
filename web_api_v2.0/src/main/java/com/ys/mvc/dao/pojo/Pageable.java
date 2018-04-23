package com.ys.mvc.dao.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/14.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pageable<T> {

    private int count;

    private int current;

    private int paging = 10;

    private int start = 0;

    private List<T> List;

    private T object;

    public Pageable<T> countStart(){
        this.start = (this.current - 1) * this.paging;
        return this;
    }

    /*public int getCount() {
        return count;
    }*/

    public Pageable<T> setCount(int count) {
        this.count = count;
        return this;
    }

    public Pageable<T> setList(java.util.List<T> list) {
        List = list;
        return this;
    }

    /*public int getCurrent() {
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



    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }*/
}
