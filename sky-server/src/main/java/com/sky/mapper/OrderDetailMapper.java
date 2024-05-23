package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {




    /**
     * 批量插入订单明细数据
     * @param orderDetails
     */
    void insertBatch(List<OrderDetail> orderDetails);


    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId}")
    List<OrderDetail> getByOrderId(Long id);

    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);
}
