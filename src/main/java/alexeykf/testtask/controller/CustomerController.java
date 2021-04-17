package alexeykf.testtask.controller;

import alexeykf.testtask.model.CustomerCreateUpdateRequest;
import alexeykf.testtask.model.CustomerDto;
import alexeykf.testtask.model.ProductCreateUpdateRequest;
import alexeykf.testtask.model.ProductDto;
import alexeykf.testtask.service.CustomerService;
import alexeykf.testtask.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{customerId}/products")
    public ProductDto createCustomerProduct(@PathVariable UUID customerId,
                                            @RequestBody @Valid ProductCreateUpdateRequest request) {
        return productService.createProduct(customerId, request);
    }

    @GetMapping("/{customerId}/products")
    public Page<ProductDto> getCustomerProducts(@PathVariable UUID customerId, Pageable pageable) {
        return productService.getProducts(customerId, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDto createCustomer(@RequestBody @Valid CustomerCreateUpdateRequest request) {
        return customerService.create(request);
    }

    @GetMapping
    public Page<CustomerDto> getCustomers(Pageable pageable) {
        return customerService.getCustomers(pageable);
    }

    @PutMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable UUID customerId,
                                      @RequestBody @Valid CustomerCreateUpdateRequest request) {
        return customerService.update(customerId, request);
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID customerId) {
        customerService.delete(customerId);
    }
}
