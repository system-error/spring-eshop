package spring.eshop.bootstrap;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spring.eshop.model.Category;
import spring.eshop.model.Product;
import spring.eshop.model.User;
import spring.eshop.repository.CategoryRepository;
import spring.eshop.repository.ProductRepository;
import spring.eshop.repository.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DBSeeder {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Product>> mapProducts = new TypeReference<>() {};
        TypeReference<List<Category>> mapCategories = new TypeReference<>() {};
        TypeReference<List<User>> mapUsers = new TypeReference<>() {};
        InputStream productIs = TypeReference.class.getResourceAsStream("/productData.json");
        InputStream categoryIs = TypeReference.class.getResourceAsStream("/categoryData.json");
        InputStream userIs = TypeReference.class.getResourceAsStream("/usersData.json");

        List<Category> categoryList = mapper.readValue(categoryIs, mapCategories);
        List<Product> productList = mapper.readValue(productIs, mapProducts);
        List<User> usersList = mapper.readValue(userIs, mapUsers);


        for(User user:usersList){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.saveAll(usersList);
        categoryRepository.saveAll(categoryList);
        for (Product product : productList) {
            product.setCategory(categoryRepository.findById(product.getCategory().getId()).get());
        }
        productRepository.saveAll(productList);
        System.out.println("Products list saved successfully");
        System.out.println("Users list saved successfully");
        System.out.println("Categories list saved successfully");


    }

}
