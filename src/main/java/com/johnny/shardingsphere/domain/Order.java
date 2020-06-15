package com.johnny.shardingsphere.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单表
 *
 * @author johnny
 * @create 2020-04-23 下午1:07
 **/
@Data
@Table(name = "tb_order")
@Entity
public class Order  implements Serializable {

    @Column(name = "id")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "user_id")
    private Long userId;


}