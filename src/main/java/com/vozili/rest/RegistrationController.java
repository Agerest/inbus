package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.repository.UsersRepository;
import com.vozili.security.RegistrationForm;
import com.vozili.serviceinterface.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegistrationController {

    private CustomerService customerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(
            CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public String registration(RegistrationForm form, Model model) {
        Customer customer = form.toUser(passwordEncoder);
        if (customerService.findByUsername(customer.getUsername()) != null) {
            model.addAttribute("error", true);
            return "Signup";
        }
        log.info(customer.toString());
        customerService.save(customer);
        return "redirect:/login";
    }

}
