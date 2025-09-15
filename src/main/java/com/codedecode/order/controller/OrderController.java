package com.codedecode.order.controller;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.service.OrderService;
import com.codedecode.order.utils.Result;
import com.codedecode.order.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public Result getAllOrders() {
        List<OrderDTO> result = orderService.getAll();
        return new Result(true, StatusCode.SUCCESS, "Retrieve All Order successful", result);
    }

    @GetMapping("/u/{userId}")
    public Result getAllOrdersForUser(@PathVariable Integer userId) {
        List<OrderDTO> result = orderService.getAllOrdersOfUser(userId);
        return new Result(true, StatusCode.SUCCESS, "Retrieve All Orders of this user successful", result);
    }

    @GetMapping("/u/{userId}/filter")
    public Result getByTimestamp(
            @PathVariable Integer userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        Date startDate = Date.from(start.atStartOfDay().toInstant(ZoneOffset.UTC));
        Date endExclusive = Date.from(end.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));

        List<OrderDTO> result = orderService.getOrdersBetween(userId, startDate, endExclusive);
        return new Result(true, StatusCode.SUCCESS, "Retrieve All orders of this user from " + start + " to " + end, result);
    }

    // Endpoint to save your order as a document in MongoDB
    //  after posting successfully, return order detail to you
    @PostMapping("/saveOrder")
    public Result saveOrder(@RequestBody OrderDTOFromFE orderDetails) { // Request body will be order details we get from FE
        // Save Order details from FE to DB
        OrderDTO orderSavedInDB =  orderService.saveOrderInDB(orderDetails);
        return new Result(true, StatusCode.SUCCESS, "Retrieve All Order successful", orderSavedInDB);
    }

    @DeleteMapping("/{id}")
    public Result deleteAnOrder(@PathVariable String id) {
        boolean result = orderService.deleteAnOrderById(id);
        return
                result ? new Result(true, StatusCode.SUCCESS, "Deleted Order successful")
                :  new Result(true, StatusCode.INTERNAL_SERVER_ERROR, "Deleted Order failed");
    }
}
