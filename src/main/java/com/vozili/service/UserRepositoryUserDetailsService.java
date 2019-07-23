package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            return customer;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    @Autowired
    public UserRepositoryUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
