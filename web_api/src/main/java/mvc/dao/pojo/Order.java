package mvc.dao.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/6/18.
 */
public class Order {

    private String id;

    private String productId;

    private String title;

    private BigDecimal price;

    private Date date;

    private String customerId;

    private String authorId;

    private float discount;

    private int status;

    private String remark;

    private String type;

    private String cover;

    private User customer;

    private User author;

    private List<ProductItemSnapshot> productItemSnapshots;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<ProductItemSnapshot> getProductItemSnapshots() {
        return productItemSnapshots;
    }

    public void setProductItemSnapshots(List<ProductItemSnapshot> productItemSnapshots) {
        this.productItemSnapshots = productItemSnapshots;
    }
}
