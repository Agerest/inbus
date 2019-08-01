package com.vozili.rest;

import com.vozili.repository.CustomerRepository;
import com.vozili.security.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/registration")
    public String registration(RegistrationForm form, Model model) {
        customerRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
