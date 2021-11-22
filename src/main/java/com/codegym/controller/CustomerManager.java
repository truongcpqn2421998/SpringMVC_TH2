package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerManager {
    CustomerService customerService = CustomerServiceFactory.getInstance();
    @GetMapping("/customers")
    public ModelAndView list() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customers/list", "customers", customers);
        return modelAndView;
    }
    @GetMapping("/info")
    public String showInfo(@RequestParam String id,Model model){
        Long id1=Long.parseLong(id);
        model.addAttribute("id",id1);
        return"customers/info";
    }
    @GetMapping("update")
    public String update(@RequestParam String id,String name,String email,String address){
        Long id1=Long.parseLong(id);
        Customer customer=customerService.findOne(id1);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        return "customers/list";
    }

}
