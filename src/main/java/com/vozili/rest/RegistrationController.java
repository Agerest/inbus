package com.vozili.rest;

import com.vozili.model.Customer;
import com.vozili.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/registration")
    public String registration(Customer user, Model model) {
        Customer userFromDB = customerRepository.findByLogin(user.getLogin());
        if (userFromDB != null) {
            model.addAttribute("message","User exists");
            return "registration";
        }
        user.setActive(true);
        user.setRole("user");
        customerRepository.save(user);
        return "redirect:/login";
    }

}
