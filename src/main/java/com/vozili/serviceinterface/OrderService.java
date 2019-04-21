package com.vozili.serviceinterface;

import com.vozili.model.Order;

import java.util.List;

public interface OrderService {
    Order getById(Long id);

    List<Order> getAll();

    Order save(Order order);

    void delete(Long id);
}
