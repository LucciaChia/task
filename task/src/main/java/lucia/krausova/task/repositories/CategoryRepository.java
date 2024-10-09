package lucia.krausova.task.repositories;

import lucia.krausova.task.entities.Category;
import lucia.krausova.task.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
