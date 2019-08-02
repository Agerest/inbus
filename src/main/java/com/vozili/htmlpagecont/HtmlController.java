package com.vozili.htmlpagecont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String hello(Model model) {
        return "Signin";
    }

    @RequestMapping(value = {"/main"}, method = RequestMethod.GET)
    public String orders(Model model) {
        return "Orders";
    }

    @RequestMapping(value = {"/main/create"}, method = RequestMethod.GET)
    public String ordersCreate(Model model) {
        return "CreateOrder";
    }

    @RequestMapping(value = {"/main/about"}, method = RequestMethod.GET)
    public String aboutUs(Model model) {
        return "AboutUs";
    }

    @RequestMapping(value = {"/main/personal"}, method = RequestMethod.GET)
    public String personalOrders(Model model) {
        return "Personal";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String registration(Model model) {
        return "Signup";
    }
}
