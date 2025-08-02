package loja.api.repository;

import loja.api.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByNameContainingIgnoreCase(String name);

    Optional<SubCategory> findById(Long id);

    List<SubCategory> findByNameIgnoreCase(String name);
}
