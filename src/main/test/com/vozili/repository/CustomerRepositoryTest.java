package com.vozili.repository;

import com.vozili.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByUsername_thenReturnCustomer() {
        Customer alex = new Customer("Alex", "h2p");
        entityManager.persist(alex);
        entityManager.flush();

        Customer found = customerRepository.findByUsername(alex.getUsername());

        assertThat(found.getUsername()).isEqualTo(alex.getUsername());
    }
}