package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.repository.CustomerRepository;
import com.vozili.serviceinterface.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }

    }

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer alex = new Customer("Alex", "12345");
        alex.setId(999L);

        Mockito.when(customerRepository.findOne(999L)).thenReturn(alex);
    }

    @Test
    public void whenValidId_thenCustomerShouldBeFound() {
        Long id = 1L;
        Customer found = customerService.getCustomer(id);

        assertThat(found.getId()).isEqualTo(id);
    }
}
