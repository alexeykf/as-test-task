package alexeykf.testtask.service;

import alexeykf.testtask.model.ProductCreateUpdateRequest;
import alexeykf.testtask.model.ProductDto;
import alexeykf.testtask.model.mapper.ProductMapper;
import alexeykf.testtask.repository.CustomerRepository;
import alexeykf.testtask.repository.ProductRepository;
import alexeykf.testtask.repository.entity.CustomerEntity;
import alexeykf.testtask.repository.entity.ProductEntity;
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
public class ProductService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto createProduct(UUID customerId, ProductCreateUpdateRequest request) {
        CustomerEntity customer = customerRepository.getById(customerId);
        ProductEntity product = productMapper.toEntity(request);
        product.setCustomer(customer);
        return productMapper.map(productRepository.save(product));
    }

    public void deleteProduct(UUID productId) {
        ProductEntity product = productRepository.getById(productId);
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public ProductDto getProduct(UUID productId) {
        return productMapper.map(productRepository.getById(productId));
    }

    public ProductDto updateProduct(UUID productId, ProductCreateUpdateRequest request) {
        ProductEntity product = productRepository.getById(productId);
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return productMapper.map(productRepository.save(product));
    }

    public Page<ProductDto> getProducts(UUID customerId, Pageable pageable) {
        return productRepository.findByCustomerId(pageable, customerId).map(productMapper::map);
    }
}
