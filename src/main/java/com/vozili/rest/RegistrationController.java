package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.repository.CustomerRepository;
import com.vozili.security.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public String registration(RegistrationForm form, Model model) {
        customerRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
