package alexeykf.testtask.repository;

import alexeykf.testtask.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByCustomerId(UUID customerId);
}
