package com.vozili.config;

import com.vozili.model.Customer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        Customer customer = new Customer("Alex","123");
        customer.setId(999L);

        return new InMemoryUserDetailsManager(Arrays.<UserDetails>asList(customer));
    }

}
