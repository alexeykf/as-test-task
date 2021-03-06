package alexeykf.testtask.service;

import alexeykf.testtask.model.CustomerCreateUpdateRequest;
import alexeykf.testtask.model.CustomerDto;
import alexeykf.testtask.model.mapper.CustomerMapper;
import alexeykf.testtask.repository.CustomerRepository;
import alexeykf.testtask.repository.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto create(CustomerCreateUpdateRequest request) {
        CustomerEntity customer = customerMapper.toEntity(request);
        customer.setCreatedAt(LocalDateTime.now());
        CustomerEntity saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }

    public CustomerDto getCustomer(UUID customerId) {
        CustomerEntity customer = customerRepository.getById(customerId);
        return customerMapper.toDto(customer);
    }

    public void delete(UUID customerId) {
        CustomerEntity customer = customerRepository.getById(customerId);
        customer.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customer);
    }

    public CustomerDto update(UUID customerId, CustomerCreateUpdateRequest request) {
        CustomerEntity customer = customerRepository.getById(customerId);
        customer.setTitle(request.getTitle());
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public Page<CustomerDto> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customerMapper::toDto);
    }
}
