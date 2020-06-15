package com.johnny.shardingsphere;

import com.johnny.shardingsphere.domain.Order;
import com.johnny.shardingsphere.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class ShardingsphereApplicationTests {


    @Autowired
    private OrderRepository orderRepository;

    RestTemplate restTemplate = new RestTemplate();

    private String getLeafId() {
        String uri = "http://localhost:8080/api/snowflake/get/test";
        String leafId = restTemplate.getForObject(uri, String.class);
        return leafId;
    }

    @Test
    void testInsert() {
        //api/snowflake/get/test 这个是leaf 获取 id的 api 接口
        //根据策略  会被定位到  ds1.tb_order1
        Order order = new Order();
        order.setId(getLeafId());
        order.setUserId(1L);   //userId  1 % 2 = 1 会分到 ds1 数据库
        order.setOrderId(1L); //orderId  1 % 2 = 1 会被分到 tb_order1 数据表
        order.setOrderName("Apache ShardingSphere 入门与精通");

        //根据策略  会被定位到  ds0.tb_order0
        Order order1 = new Order();
        order1.setId(getLeafId());
        order1.setUserId(2L);  //userId  2 % 2 = 0 会分到 ds0 数据库
        order1.setOrderId(2L);  //orderId  2 % 2 = 0 会被分到 tb_order0 数据表
        order1.setOrderName("Kotlin 入门到精通");

        orderRepository.save(order);
        orderRepository.save(order1);
    }

    @Test
    void textSelect() {
        List<Order> orderList = orderRepository.findAll();
        System.out.println(orderList);
    }


    @Test
    void testLeafId() {
        RestTemplate restTemplate = new RestTemplate();
        //api/snowflake/get/test 这个是leaf 获取 id的 api 接口
        String uri = "http://localhost:8080/api/snowflake/get/test";
        for (int i = 0; i < 500; i++) {
            String leafId = restTemplate.getForObject(uri, String.class);
            System.out.println(leafId);
        }
    }

}
