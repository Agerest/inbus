package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.OrderRepository;
import com.vozili.repository.UsersRepository;
import com.vozili.serviceinterface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Order getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer savePersonalOrder(Order order, Customer customer) {
        Order result = repository.save(order);
        customer.setPersonalOrder(result);
        usersRepository.save(customer);
        return customer;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
