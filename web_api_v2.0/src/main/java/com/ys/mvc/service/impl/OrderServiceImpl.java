package com.ys.mvc.service.impl;

import com.ys.mvc.dao.mapper.OrderMapper;
import com.ys.mvc.dao.mapper.ProductItemSnapshotMapper;
import com.ys.mvc.dao.mapper.ProductMapper;
import com.ys.mvc.dao.pojo.*;
import com.ys.mvc.service.OrderService;
import com.ys.utils.enumeration.OrderStatus;
import com.ys.utils.enumeration.RoleCode;
import com.ys.utils.tools.Lang;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return pageable.countStart()
                .setList(orderMapper.getList(pageable))
                .setCount(orderMapper.count());
    }

    public List<Order> getUserOrder(User user) {
        return orderMapper.getUserOrder(user.getId());
    }

    @Transactional
    public void addOrder(Order order) {
        Product product = productMapper.findById(order.getProductId());
        order.setId("92YS_" + product.getType().toUpperCase() + "_" + new Date().getTime());
        order.setType(product.getType());
        order.setAuthorId(product.getAuthorId());
        order.setCover(product.getCover());
        order.setDate(new Date());
        order.setDiscount(product.getDiscount());
        order.setTitle(product.getTitle());
        order.setStatus(OrderStatus.WORKING);
        order.setHaveAttachment(product.getHaveAttachment());
        order.setAttachmentURL(product.getAttachmentURL());
        order.setAttachmentPassword(product.getAttachmentPassword());
        order.setDirector(product.getDirector());
        order.setDirectorPhone(product.getDirectorPhone());
        orderMapper.add(order);

        //先增加父节点
        if(order.getProductItemSnapshots() != null){
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

    @Override
    public void closeOrder(Order order) {
        orderMapper.closeOrder(order.getId(), order.getCustomerId());
    }

    @Override
    public Order getOrderDetail(Order order) {
        Order existOrder = orderMapper.findById(order.getId(), order.getCustomerId());

        List<ProductItemSnapshot> items = existOrder.getProductItemSnapshots();
        if(items != null){
            for(ProductItemSnapshot t : items){
                t.setProductItemSnapshots(productItemSnapshotMapper.findItemByPid(t.getId()));
            }
        }
        return existOrder;
    }
}
