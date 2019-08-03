package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.UsersRepository;
import com.vozili.serviceinterface.CustomerService;
import com.vozili.serviceinterface.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> result = this.orderService.getAll();
        if (result.isEmpty()) {
            return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
        }
        log.info(result.toString());
        return new ResponseEntity<List<Order>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @AuthenticationPrincipal UserDetails user) {
        Customer customer = customerService.getCustomer(user.getUsername());
        orderService.savePersonalOrder(order, customer);
        log.info(order.toString());
        return new ResponseEntity<Order>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        Customer customer = customerService.getCustomer(user.getUsername());
        Order order = orderService.getById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        log.info(order.toString());
        customer.setPersonalOrder(null);
        customerService.save(customer);
        orderService.delete(id);
        log.info(customer.toString());
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}
