package alexeykf.testtask.repository;

import alexeykf.testtask.exception.CustomerNotFoundException;
import alexeykf.testtask.repository.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, UUID> {

    default CustomerEntity getById(UUID customerId) {
        return findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
