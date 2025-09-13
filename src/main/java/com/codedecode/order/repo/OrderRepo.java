package com.codedecode.order.repo;
import com.codedecode.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

// This will be a MongoDB Repository that will connect and query a MongoDB datasource
@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {

    List<Order> findByUserId(Integer userId);

}
