package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hi")
    public String hello() {
        return "hi";
    }
}
