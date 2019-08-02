package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.repository.UsersRepository;
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

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(
            UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public String registration(RegistrationForm form, Model model) {
        Customer customer = form.toUser(passwordEncoder);
        log.info(customer.toString());
        usersRepository.save(customer);
        return "redirect:/login";
    }

}
