package spring.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spring.eshop.model.FileInfo;
import spring.eshop.model.Product;
import spring.eshop.model.User;
import spring.eshop.repository.CategoryRepository;
import spring.eshop.repository.ProductRepository;
import spring.eshop.service.FilesStorageService;
import spring.eshop.service.ProductService;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    FilesStorageService filesStorageService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/administrator/products")
    public String displayProductsInAdmin(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "admin/adminProducts";
    }

    @GetMapping("/administrator/addProduct")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("product",product);
        return "admin/adminProductsAdd";
    }

    @PostMapping("/administrator/products")
    public String addProduct(@Valid Product product, Model model,@RequestParam("image") MultipartFile file){

//        System.out.println(product.getImage().getOriginalFilename());
//        System.out.println(product.getImage().getContentType());
//        System.out.println(product.getImage().getResource());
//        System.out.println(product.getImage().getName());
//        System.out.println(product);
//        System.out.println(file.getName());
//        System.out.println(file.getResource());
//        System.out.println(file.getOriginalFilename());

        product.setImageName(product.getImage().getOriginalFilename());

        filesStorageService.init();
        filesStorageService.save(file);
        productRepository.save(product);
        model.addAttribute("message","Products added successfully!!!!");

        model.addAttribute("products",productService.getAllProducts());

        return "admin/adminProducts";

    }

}
