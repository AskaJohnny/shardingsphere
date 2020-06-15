package com.johnny.shardingsphere.repository;

import com.johnny.shardingsphere.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author johnny
 * @create 2020-04-23 下午1:12
 **/
public interface OrderRepository extends JpaRepository<Order, Long> {


}