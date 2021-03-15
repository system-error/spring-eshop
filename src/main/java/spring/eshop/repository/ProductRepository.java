package spring.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.eshop.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
