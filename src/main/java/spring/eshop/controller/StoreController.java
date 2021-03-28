package spring.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import spring.eshop.model.Product;
import spring.eshop.repository.ProductRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/products/all")
    public String productsAll(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "products";
    }
}
