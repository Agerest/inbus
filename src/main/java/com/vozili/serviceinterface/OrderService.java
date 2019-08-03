package com.vozili.serviceinterface;

import com.vozili.model.Customer;
import com.vozili.model.Order;

import java.util.List;

public interface OrderService {
    Order getById(Long id);

    List<Order> getAll();

    Customer savePersonalOrder(Order order, Customer customer);

    void delete(Long id);
}
