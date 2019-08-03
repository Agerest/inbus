package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.UsersRepository;
import com.vozili.serviceinterface.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UsersRepository usersRepository;

    @Bean("UserRepositoryUserDetailsService")
    public UserDetailsService userDetailsService() {
        return new UserRepositoryUserDetailsService(usersRepository);
    }

    @Override
    public Customer getCustomer(String username) {
        return usersRepository.findOne(username);
    }

    @Override
    public Order getBookedOrder(String username) {
        return usersRepository.findByUsername(username).getBookedOrder();
    }

    @Override
    public Order getPersonalOrder(String username) {
        return usersRepository.findByUsername(username).getPersonalOrder();
    }

    @Override
    public Customer save(Customer customer) {
        return usersRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
