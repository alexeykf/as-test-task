package alexeykf.testtask.model.mapper;

import alexeykf.testtask.model.ProductCreateUpdateRequest;
import alexeykf.testtask.model.ProductDto;
import alexeykf.testtask.repository.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(ProductCreateUpdateRequest from);

    ProductDto map(ProductEntity from);
}
