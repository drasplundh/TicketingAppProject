//package dev.duncan.controller;
//
//import dev.duncan.domain.EmailDetails;
//import dev.duncan.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class EmailController {
//
//    @Autowired
//    EmailService emailService;
//
//    @PostMapping("/sendMail")
//    public String
//    sendMail(@RequestBody EmailDetails details)
//    {
//        String status
//                = emailService.sendSimpleEmail(details);
//
//        return status;
//    }
//}
