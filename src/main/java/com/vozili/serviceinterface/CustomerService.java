package com.vozili.serviceinterface;

import com.vozili.model.Customer;
import com.vozili.model.Order;

public interface CustomerService {
    Customer getCustomer(String username);

    Order getBookedOrder(String username);

    Order getPersonalOrder(String username);

    Customer save(Customer customer);

    Customer findByUsername(String username);
}
