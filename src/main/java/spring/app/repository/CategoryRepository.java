package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.app.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
