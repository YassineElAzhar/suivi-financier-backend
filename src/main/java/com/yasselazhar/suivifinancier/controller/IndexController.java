package com.yasselazhar.suivifinancier.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String hello() {
        return "Backend de suivi-finiancier. l'api se trouve sous /suivi-financier/.";
    }
}
