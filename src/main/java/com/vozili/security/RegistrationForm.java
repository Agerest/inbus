package com.vozili.security;

import com.vozili.model.Customer;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;

    public Customer toUser(PasswordEncoder passwordEncoder) {
        return new Customer(username, passwordEncoder.encode(password));
    }

}
