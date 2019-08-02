package com.vozili.repository;

import com.vozili.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Customer, String> {

    Customer findByUsername(String username);

}
