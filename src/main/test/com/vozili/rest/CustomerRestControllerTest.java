package com.vozili.rest;

import com.vozili.config.SpringSecurityWebAuxTestConfig;
import com.vozili.model.Customer;
import com.vozili.model.Order;
import com.vozili.repository.CustomerRepository;
import com.vozili.serviceinterface.CustomerService;
import com.vozili.serviceinterface.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @WithUserDetails("Alex")
    public void givenOrder_whenGetBookedOrder_thenReturnJsonArray() throws Exception {
        Order order = new Order();
        order.setId(999L);

        given(customerService.getBookedOrder("Alex")).willReturn(order);

        mvc.perform(MockMvcRequestBuilders.get("/customer/booked")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order.getId().intValue())));
    }

    @Test
    @WithUserDetails("Alex")
    public void givenOrder_whenGetPersonalOrder_thenReturnJsonArray() throws Exception {
        Order order = new Order();
        order.setId(999L);

        given(customerService.getPersonalOrder("Alex")).willReturn(order);

        mvc.perform(MockMvcRequestBuilders.get("/customer/personal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order.getId().intValue())));
    }

    @Test
    @WithUserDetails("Alex")
    public void givenCustomer_whenGetCustomer_thenReturnJsonArray() throws Exception {

        Customer customer = new Customer("Alex", "123");

        given(customerRepository.findByUsername("Alex")).willReturn(customer);

        mvc.perform(MockMvcRequestBuilders.get("/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(customer.getUsername())));
    }

    @Test
    @WithUserDetails("Alex")
    public void givenCustomerWithBookedOrder_whenSetBookedOrder_thenReturnJsonArray() throws Exception {
        Long id = 99L;

        Customer customer = new Customer("Alex", "123");

        Order order = new Order();
        order.setId(999L);


        given(customerRepository.findByUsername(customer.getUsername())).willReturn(customer);
        given(orderService.getById(id)).willReturn(order);
        when(customerRepository.save(customer)).thenReturn(customer);

        mvc.perform(MockMvcRequestBuilders.put("/customer/booked/" + id.intValue())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookedOrder.id", is(order.getId().intValue())));
    }

    @Test
    @WithUserDetails("Alex")
    public void givenCustomerWithoutBookedOrder_whenDeleteBookedOrder_thenReturnJsonArray() throws Exception {
        Customer customer = new Customer("Alex", "123");

        Order order = new Order();
        order.setId(999L);

        customer.setBookedOrder(order);

        given(customerRepository.findByUsername(customer.getUsername())).willReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        mvc.perform(MockMvcRequestBuilders.delete("/customer/booked")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookedOrder", nullValue()));
    }

}