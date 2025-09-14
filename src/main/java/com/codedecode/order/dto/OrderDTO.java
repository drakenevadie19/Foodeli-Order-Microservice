package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String id;

    private Integer orderId;
    private List<FoodItemsDTO> foodItemsList;
    private Integer restaurantId;
    private Integer userId;
    private Date timestamp;
    private BigDecimal price;

}
