package com.OrderService.OrderService.controller;

import com.OrderService.OrderService.model.Order;
import com.OrderService.OrderService.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController

public class OrderController
{
    @Autowired
    private OrderRepo orderRepo;


    @GetMapping("/searchBook/{title}")
    public ResponseEntity<?> getOrder(@PathVariable String title)
    {
        String uri = "http://localhost:9090/getBookByTitle/" + title;
        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<?> response = template.getForEntity(uri, Object.class);

            // Check if the book was found
            if (response.getStatusCode() == HttpStatus.OK) {
                // Book found, process the response body
                return response;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Book not found, handle the case
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND); // Or return an empty list
            } else {
                // Handle other unexpected errors
                return new ResponseEntity<>("An error occurred while retrieving the book", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (RestClientException e) {
            // Handle communication errors
            return new ResponseEntity<>("Book not found", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> addBook(@RequestBody Order order)
    {
        Order orderObj = orderRepo.save(order);
        return new ResponseEntity<>(orderObj, HttpStatus.OK);
    }

    @GetMapping("/getAllorder")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        try
        {
            List<Order>  orderList = new ArrayList<>();
            orderRepo.findAll().forEach(orderList::add);
            if(orderList.isEmpty())
            {
                return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orderList ,HttpStatus.OK);
        } catch (Exception ex)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
