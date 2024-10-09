package lucia.krausova.task.repositories;

import lucia.krausova.task.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
