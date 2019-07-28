package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.CustomerRepository;
import com.vozili.serviceinterface.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Order getBookedOrder(Long id) {
        return customerRepository.findOne(id).getBookedOrder();
    }

    @Override
    public Order getPersonalOrder(Long id) {
        return customerRepository.findOne(id).getPersonalOrder();
    }
}
