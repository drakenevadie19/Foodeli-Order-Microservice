package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDB(OrderDTOFromFE orderDetails) {

        // firstly generate next OrderID for just placed
        int newOrderId = sequenceGenerator.generateNextOrderId();

        // Get user details from given userId from RequestBody
//        UserDTO userDetails = fetchUserDetailsFromUserId(orderDetails.getUserId());

        // The task here is to add list of items and restaurant
        // For userId, get entire user details, then add it in order Entity
        // There are 4 parameter to generate a Order
        //  1. orderId = generate from above function
        //  2. foodItemsList = getting from RequestBody
        //  3. restaurant = getting from RequestBody
        //  4. userId = getting from RequestBody
        //  5. timestamp = getting from RequestBody
        //  6. price = getting from RequestBody
        Order orderToBeSaved = new Order(null, newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurantId(), orderDetails.getUserId(), orderDetails.getTimestamp(), orderDetails.getPrice());

        // Save created order to MongoDB
        orderRepo.save(orderToBeSaved);

        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderRepo.findByOrderId(newOrderId));
    }

//    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
//        // Use RestTemplate to access Into userInfo MS to access to userInfo MySQL DB to extract all info of user
//        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
//    }

    public List<OrderDTO> getAll() {
        List<Order> result = orderRepo.findAll();
//        result.forEach(o -> System.out.println("DEBUG Order = " + o));

        List<OrderDTO> result1 = new ArrayList<>();
        for (int i=0;i<result.size();i++) {
            result1.add(OrderMapper.INSTANCE.mapOrderToOrderDTO(result.get(i)));
        }

        return result1;
    }

    public List<OrderDTO> getAllOrdersOfUser(Integer userId) {
        List<Order> result = orderRepo.findByUserId(userId);
        List<OrderDTO> result1 = new ArrayList<>();
        for (int i=0;i<result.size();i++) {
            result1.add(OrderMapper.INSTANCE.mapOrderToOrderDTO(result.get(i)));
        }

        return result1;
    }

    public boolean deleteAnOrderById(String id) {
        try {
            orderRepo.deleteById(id);
        } catch (Error e) {
            return false;
        }

        return true;
    }
}
