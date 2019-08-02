package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;

    public UserRepositoryUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = usersRepository.findByUsername(username);
        if (customer != null) {
            log.info(customer.toString());
            return customer;
        }
        log.info("User '" + username + "' not found");
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

}
