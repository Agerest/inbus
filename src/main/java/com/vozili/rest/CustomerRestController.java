package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.UsersRepository;
import com.vozili.serviceinterface.CustomerService;
import com.vozili.serviceinterface.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/booked", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getBookedOrder(@AuthenticationPrincipal UserDetails customer) {
        Order result = customerService.getBookedOrder(customer.getUsername());
        if (result == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        log.info(result.toString());
        return new ResponseEntity<Order>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getPersonalOrder(@AuthenticationPrincipal UserDetails customer) {
        Order result = customerService.getPersonalOrder(customer.getUsername());
        if (result == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        log.info(result.toString());
        return new ResponseEntity<Order>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@AuthenticationPrincipal UserDetails user) {
        Customer customer = customerService.findByUsername(user.getUsername());
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        log.info(customer.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/booked/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> setBookedOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        Customer customer = customerService.findByUsername(user.getUsername());
        Order order = orderService.getById(id);
        customer.setBookedOrder(order);
        customerService.save(customer);
        log.info(customer.toString());
        if (order == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        log.info(order.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/booked", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteBookedOrder(@AuthenticationPrincipal UserDetails user) {
        Customer customer = customerService.findByUsername(user.getUsername());
        customer.setBookedOrder(null);
        customerService.save(customer);
        log.info(customer.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
