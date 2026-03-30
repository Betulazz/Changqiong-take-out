package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单
     */
    @Scheduled(cron = "0 * * * * ?") // 每分钟触发一次
//    @Scheduled(cron = "1/5 * * * * ?")
    public void processTimeOutOrder() {
        log.info("定时处理超时订单:{}", LocalDateTime.now());

        // select * from orders where status = ? and order_time < (当前时间-15分钟)
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if (!CollectionUtils.isEmpty(ordersList)) {
            for (Orders orders : ordersList) {
                // 更新订单状态
                Orders update = Orders.builder()
                        .id(orders.getId())
                        .status(Orders.CANCELLED)
                        .cancelReason("订单超时，自动取消")
                        .cancelTime(LocalDateTime.now())
                        .build();
                orderMapper.update(update);
            }
        }

    }

    /**
     * 处理一直处于派送中状态的订单
     */
    @Scheduled(cron = "0 0 1 * * ?") // 每天凌晨1点触发一次
//    @Scheduled(cron = "0/5 * * * * ?")
    public void processCompletedOrder() {
        log.info("处理一直处于派送中状态的订单:{}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));
        if (!CollectionUtils.isEmpty(ordersList)) {
            for (Orders orders : ordersList) {
                // 更新订单状态
                Orders update = Orders.builder()
                        .id(orders.getId())
                        .status(Orders.COMPLETED)
                        .cancelReason("订单超时，自动取消")
                        .cancelTime(LocalDateTime.now())
                        .build();
                orderMapper.update(update);
            }
        }

    }
}
