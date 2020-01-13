package courseWork.controllers;


import com.fasterxml.jackson.annotation.JsonProperty;
import courseWork.logic.Order;
import courseWork.service.OrderProductService;
import courseWork.service.OrderService;
import courseWork.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping(path="/shop")
public class OrderController {
    @Autowired
    productService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderProductService orderProductService;
    public OrderController(productService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }
    @GetMapping(path="/getOrder/{order_id}")
    public @ResponseBody
    Optional<Order> getOrderProductsByID(@PathVariable("order_id")  int order_id) {
        // This returns a JSON or XML with the users
       return orderService.getOrderbyId(order_id);
    }

    @GetMapping(path="/getAllOrder/")
    public @ResponseBody @NotNull Iterable<Order> getOrderProducts() {
        // This returns a JSON or XML with the users
        return this.orderService.getAllOrders();
    }

    @GetMapping(path="/getLastOrder/")
    public @ResponseBody @NotNull Order getLastOrder() {
        // This returns a JSON or XML with the users
        return this.orderService.getLastOrder();
    }

    @GetMapping(value = "/index")
    public String list() {
        return "start.html";
    }

    @GetMapping(path="/getOrdersByClient/{client_id}")
    public @ResponseBody
    Iterable<Order> getOrdersByClientId(@PathVariable("client_id")  int client_id) {
        // This returns a JSON or XML with the users
        return orderService.getOrdersByClientId(client_id);
    }
    @PostMapping(path = "/neworder/",
            consumes = "application/json")
    public @ResponseBody
    //ResponseEntity<String> clientOrder(@PathVariable("client_id")  int client_id,  @PathVariable("cost") Float cost,@PathVariable("list")List<Integer> productsoforder) {
    //ResponseEntity<String> clientOrder( Integer client_id,   Float cost, List<Integer> productsoforder) {
    ResponseEntity<String> clientOrder(@RequestBody  OrderWrapper order) {
        // This returns a JSON or XML with the users
        Order lastorder =  this.orderService.getLastOrder();
        Integer lastOrderId  =  lastorder.getOrderId();
        List<Integer> productsoforder = new ArrayList<Integer>();
        productsoforder = order.getproductsoforder();
        int client_id = order.client_id;
        Float cost = order.cost;
        try {
            orderService.createOrder(lastOrderId+1, client_id,  productsoforder, cost);
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public static class OrderWrapper{

        @JsonProperty  public int client_id;
        @JsonProperty public Float cost;
       @JsonProperty List<Integer> list;
        public OrderWrapper(){}
        public OrderWrapper (int client_id, Float cost, List<Integer> productsoforder)
        {
            this.client_id=client_id;
            this.cost=cost;
           setproductsoforder(productsoforder);
        }
        public List<Integer> getproductsoforder() {
            return list;
        }

        public void setproductsoforder(List<Integer> productsoforder) {
            this.list = productsoforder;
        }


    }


}
