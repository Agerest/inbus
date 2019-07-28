package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.CustomerRepository;
import com.vozili.serviceinterface.CustomerService;
import com.vozili.serviceinterface.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/booked", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getBookedOrder(@AuthenticationPrincipal Customer customer) {
        Order result = customerService.getBookedOrder(customer.getId());
        if (result == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        log.info(result.toString());
        return new ResponseEntity<Order>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getPersonalOrder(@AuthenticationPrincipal Customer customer) {
        Order result = customerService.getPersonalOrder(customer.getId());
        if (result == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        log.info(result.toString());
        return new ResponseEntity<Order>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@AuthenticationPrincipal Customer customer) {
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        log.info(customer.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/booked/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> setBookedOrder(@PathVariable Long id, @AuthenticationPrincipal Customer customer) {
        Order order = orderService.getById(id);
        customer.setBookedOrder(order);
        customerRepository.save(customer);
        log.info(customer.toString());
        if (order == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        log.info(order.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/booked", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteBookedOrder(@AuthenticationPrincipal Customer customer) {
        customer.setBookedOrder(null);
        customerRepository.save(customer);
        log.info(customer.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
