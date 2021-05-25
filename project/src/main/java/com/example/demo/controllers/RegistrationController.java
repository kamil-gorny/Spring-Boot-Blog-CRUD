package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.services.impl.MailServiceImpl;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.validation.Valid;


@Controller
public class RegistrationController {
    private final UserService userService;
    private final MailServiceImpl mailService;

    @Autowired
    public RegistrationController(UserService userService, MailServiceImpl mailService){
        this.userService = userService;
        this.mailService = mailService;
    }

    @RequestMapping(value="/registration", method= RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "/registration";
    }
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String createNewUser(@Valid @ModelAttribute("user") User user,  BindingResult bindingResult, Model model) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Użytkownik o takim adresie Email już istnieje");
        }
        if(user.getUsername().equals(user.getFirstName().charAt(0)+user.getLastName())){
           if(userService.findByUsername(user.getUsername()).isPresent()){
               bindingResult.rejectValue("username", "error.user",
                       "Użytkownik o takiej nazwie użytkownika już istnieje");
           }
        }
        else {
            if(userService.findByUsername(user.getFirstName().charAt(0)+user.getLastName()).isPresent()){
                bindingResult.rejectValue("username", "error.user",
                        "Użytkownik o takiej samej domyślnej nazwie użytkownika już istnieje, wybierz inną");

            }
        }

        if(!bindingResult.hasErrors()){
            userService.save(user);
            model.addAttribute("success", "Zostales zarejestrowany!");
            model.addAttribute("user", new User());
            mailService.sendMail(user.getEmail(), "Zostałeś zarejestrowany!", "Udalo ci się zarejestrować", true);
        }



        return "/registration";
    }
}
