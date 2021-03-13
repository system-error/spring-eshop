package spring.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/products/all")
    public String productsAll() {
        return "products";
    }
}
