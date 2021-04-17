package alexeykf.testtask.repository;

import alexeykf.testtask.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByCustomerId(Pageable pageable, UUID customerId);

    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.customer c WHERE p.id = :productId AND c.deletedAt IS NULL")
    Optional<ProductEntity> findById(UUID productId);
}
