package alexeykf.testtask.service;

import alexeykf.testtask.exception.CustomerNotFoundException;
import alexeykf.testtask.model.CustomerCreateUpdateRequest;
import alexeykf.testtask.model.CustomerDto;
import alexeykf.testtask.model.mapper.CustomerMapper;
import alexeykf.testtask.repository.CustomerRepository;
import alexeykf.testtask.repository.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto create(CustomerCreateUpdateRequest request) {
        CustomerEntity customer = customerMapper.toEntity(request);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto getCustomer(UUID customerId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        return customerMapper.toDto(customer);
    }

    public void delete(UUID customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.save(customer);
    }

    public CustomerDto update(UUID customerId, CustomerCreateUpdateRequest request) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        customer.setTitle(request.getTitle());
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public Page<CustomerDto> getCustomers() {
        return customerRepository.findAll(PageRequest.of(0, 100)).map(customerMapper::toDto);
    }
}
