package spring.eshop.bootstrap;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.eshop.model.Category;
import spring.eshop.model.Product;
import spring.eshop.repository.CategoryRepository;
import spring.eshop.repository.ProductRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
                        List<Product> productList = mapper.readValue(productIs, mapProducts);
                        List<Category> categoryList = mapper.readValue(categoryIs, mapCategories);
                        productRepository.saveAll(productList);
                        categoryRepository.saveAll(categoryList);
                        System.out.println("Products list saved successfully");
                        System.out.println("Categories list saved successfully");
                } catch (IOException e) {
                        System.out.println(e.getMessage());
                }
//            Article article1 = new Article();
//            article1.setContent("Java is cool.");
//            articleRepository.save(article1);
//            Article article2 = new Article();
//            article2.setContent("Java will fail.");
//            articleRepository.save(article2);
//
//            Comment comment1 = new Comment();
//            comment1.setContent("I don't agree");
//            comment1.setArticle(article1);
//            commentRepository.save(comment1);
//            Comment comment2 = new Comment();
//            comment2.setContent("Not really");
//            comment2.setArticle(article2);
//            commentRepository.save(comment2);
        }

}
