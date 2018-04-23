package mvc.dao.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by peijian.wang on 2017/4/3.
 */
public class ProductItemSnapshot {

    private String id;

    private String name;

    private String pid;

    private BigDecimal price;

    private int isItem;

    private int no;

    private int num;

    private List<ProductItemSnapshot> productItemSnapshots;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getIsItem() {
        return isItem;
    }

    public void setIsItem(int isItem) {
        this.isItem = isItem;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ProductItemSnapshot> getProductItemSnapshots() {
        return productItemSnapshots;
    }

    public void setProductItemSnapshots(List<ProductItemSnapshot> productItemSnapshots) {
        this.productItemSnapshots = productItemSnapshots;
    }
}
