package loja.api.repository;

import loja.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByNameIgnoreCase(String name);

    @Override
    void deleteById(Long aLong);
}

