package spring.eshop.bootstrap;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.eshop.model.Category;
import spring.eshop.model.Product;
import spring.eshop.repository.CategoryRepository;
import spring.eshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBSeeder {

        @Autowired
        ProductRepository productRepository;

        @Autowired
        CategoryRepository categoryRepository;

        @EventListener
        public void seed(ContextRefreshedEvent event) {

                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Product>> mapProducts = new TypeReference<>() {};
                TypeReference<List<Category>> mapCategories = new TypeReference<>() {};
                InputStream productIs = TypeReference.class.getResourceAsStream("/productData.json");
                InputStream categoryIs = TypeReference.class.getResourceAsStream("/categoryData.json");

                try {
                        List<Category> categoryList = mapper.readValue(categoryIs, mapCategories);
                        List<Product> productList = mapper.readValue(productIs, mapProducts);
                        Set<Product> products = new HashSet<>();

                        for(Product product: productList){
                                System.out.println(product.getCategory().getId());
                                products.add(product);
                        }
                        productRepository.saveAll(productList);

                        for(Category category: categoryList){
                                System.out.println(category.getName());
                                category.setProducts(products);
                        }

                        categoryRepository.saveAll(categoryList);
                        System.out.println("Products list saved successfully");
                        System.out.println("Categories list saved successfully");
                } catch (IOException e) {
                        System.out.println(e.getMessage());
                }










        }

}
