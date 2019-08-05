package com.vozili.service;

import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.OrderRepository;
import com.vozili.repository.UsersRepository;
import com.vozili.serviceinterface.OrderService;
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
public class OrderServiceImplTest {

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl();
        }

    }

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void whenValidId_thenOrderShouldBeFound() {
        Long id = 999L;
        Order order = new Order();
        order.setId(id);

        Mockito.when(orderRepository.getOne(999L)).thenReturn(order);

        Order found = orderService.getById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenSavePersonalOrder_thenReturnCustomerWithPersonalOrder() {
        Order order = new Order();
        order.setId(999L);
        Customer customer = new Customer("Alex", "123", true);

        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(usersRepository.save(customer)).thenReturn(customer);

        Customer found = orderService.savePersonalOrder(order, customer);

        assertThat(found.getPersonalOrder().getId()).isEqualTo(order.getId());
    }

}