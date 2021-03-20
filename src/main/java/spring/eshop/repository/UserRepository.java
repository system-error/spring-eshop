package spring.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.eshop.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
