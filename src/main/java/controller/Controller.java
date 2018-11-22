package main.java.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String test() {
        System.out.println("hihihih");
        return "index";
    }
}
