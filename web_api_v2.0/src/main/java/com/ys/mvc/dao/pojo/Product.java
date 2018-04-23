package com.ys.mvc.dao.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/3/26.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    private String id;

    private String type;

    private Integer haveAttachment;

    private String title;

    private String authorId;

    private String cover;

    private String content;

    private String resourcesTypeId;

    private String articleId;

    private float discount;

    private Date updateTime;

    private String attachmentURL;

    private String attachmentPassword;

    private String director;

    private String directorPhone;

    private User user;

    private ResourcesType resourcesType;

    private List<ProductItem> productItems;

    private List<Carousel> carousels;

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHaveAttachment() {
        return haveAttachment;
    }

    public void setHaveAttachment(Integer haveAttachment) {
        this.haveAttachment = haveAttachment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourcesTypeId() {
        return resourcesTypeId;
    }

    public void setResourcesTypeId(String resourcesTypeId) {
        this.resourcesTypeId = resourcesTypeId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResourcesType getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(ResourcesType resourcesType) {
        this.resourcesType = resourcesType;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public List<Carousel> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<Carousel> carousels) {
        this.carousels = carousels;
    }*/
}
