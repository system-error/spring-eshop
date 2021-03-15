package spring.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.eshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
