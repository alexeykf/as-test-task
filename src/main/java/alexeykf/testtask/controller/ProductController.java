package alexeykf.testtask.controller;

import alexeykf.testtask.model.ProductCreateUpdateRequest;
import alexeykf.testtask.model.ProductDto;
import alexeykf.testtask.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

import static alexeykf.testtask.Const.API_V1;

@Validated
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable UUID productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable UUID productId, @RequestBody @Valid ProductCreateUpdateRequest request) {
        return productService.updateProduct(productId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }
}
