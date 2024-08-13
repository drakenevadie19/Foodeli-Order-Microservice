package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.dto.UserDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        UserDTO userDetails = fetchUserDetailsFromUserId(orderDetails.getUserId());

        // The task here is to add list of items and restaurant
        // For userId, get entire user details, then add it in order Entity
        // There are 4 parameter to generate a Order
        //  1. orderId = generate from above function
        //  2. foodItemsList = getting from RequestBody
        //  3. restaurant = getting from RequestBody
        //  4. UserDTo = user details, which is NOT gotten from RequestBody (RequestBody only have userId
        //      => Have to get that user details based on userId (query)
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDetails);

        // Save created order to MongoDB
        orderRepo.save(orderToBeSaved);

        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        // Use RestTemplate to access Into userInfo MS to access to userInfo MySQL DB to extract all info of user
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/"+userId, UserDTO.class);
    }
}
