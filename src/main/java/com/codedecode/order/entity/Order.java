package com.codedecode.order.entity;

import com.codedecode.order.dto.FoodItemsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order") // Make this entity as a document (JSON) in MongoDB
public class Order {

    // If I use MySQL and Hibernate, it will automatically generate an id for me and save in hibernate_sequence
    //  However, since I am using MongoDB now, I can not use that feature, so we have to save orderId here
    @Id
//    @Field("_id")
    private String id;

    private Integer orderId;
    private List<FoodItemsDTO> foodItemsList;
    private Integer restaurantId;
    private Integer userId;
    private Date timestamp;
    private BigDecimal price;
}
