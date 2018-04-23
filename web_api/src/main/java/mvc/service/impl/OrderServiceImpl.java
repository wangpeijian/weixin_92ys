package mvc.service.impl;

import mvc.dao.maps.OrderMapper;
import mvc.dao.maps.ProductItemSnapshotMapper;
import mvc.dao.maps.ProductMapper;
import mvc.dao.pojo.*;
import mvc.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.enumeration.OrderStatus;
import utils.tools.Lang;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/6/18.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = Logger.getLogger(OrderServiceImpl.class);

    private final OrderMapper orderMapper;

    private final ProductItemSnapshotMapper productItemSnapshotMapper;

    private ProductMapper productMapper;

    public OrderServiceImpl(OrderMapper orderMapper, ProductItemSnapshotMapper productItemSnapshotMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.productItemSnapshotMapper = productItemSnapshotMapper;
        this.productMapper = productMapper;
    }

    public Pageable<Order> getList(Pageable<Order> pageable, User user) {

        orderMapper.getList(pageable);

        return null;
    }

    public List<Order> getUserOrder(User user) {
        return orderMapper.getUserOrder(user.getId());
    }

    @Transactional
    public void addOrder(Order order) {
        order.setId(Lang.getUUID());
        Product product = productMapper.findById(order.getProductId());
        order.setType(product.getType());
        order.setAuthorId(product.getAuthorId());
        order.setCover(product.getCover());
        order.setDate(new Date());
        order.setDiscount(product.getDiscount());
        order.setTitle(product.getTitle());
        order.setStatus(OrderStatus.WORKING);
        orderMapper.add(order);

        //先增加父节点
        for(ProductItemSnapshot o : order.getProductItemSnapshots()){
            o.setId(Lang.getUUID());
            o.setPid(order.getId());
            productItemSnapshotMapper.add(o);
            for(ProductItemSnapshot s : o.getProductItemSnapshots()){
                s.setId(Lang.getUUID());
                s.setPid(o.getId());
                productItemSnapshotMapper.add(s);
            }
        }
    }
}
