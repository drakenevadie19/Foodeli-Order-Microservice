package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
// These are getting from Front-end after pushing Order Now button
public class OrderDTOFromFE {

    // If I use MySQL and Hibernate, it will automatically generate an id for me and save in hibername_sequence
    //  However, since I am using MongoDB now, I can not use that feature => go to /entity/Order.java => define an Id for Order there

    // Get these fields from FE
    private List<FoodItemsDTO> foodItemsList;
    private Integer restaurantId;
    private Integer userId;
    private Date timestamp;
    private BigDecimal price;

}
