package com.vozili.rest;

import com.vozili.config.SpringSecurityWebAuxTestConfig;
import com.vozili.model.Order;
import com.vozili.serviceinterface.CustomerService;
import com.vozili.serviceinterface.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Test
    @WithUserDetails(value = "Alex", userDetailsServiceBeanName = "testUserDetailsService")
    public void givenListOrders_whenGetAllOrders_thenReturnJsonArray() throws Exception {
        List<Order> orders = new ArrayList<Order>();
        Order order = new Order();
        order.setId(999L);
        orders.add(order);

        given(orderService.getAll()).willReturn(orders);

        mvc.perform(MockMvcRequestBuilders.get("/orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(order.getId().intValue())));
    }

}