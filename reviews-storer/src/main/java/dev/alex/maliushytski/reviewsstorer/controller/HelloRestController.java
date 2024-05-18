package dev.alex.maliushytski.reviewsstorer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hello/{text}")
    public String helloMyFriend(@PathVariable(name = "text") String text) {
        return "welcome " + text.toUpperCase();
    }
}
