package alexeykf.testtask.model.mapper;


import alexeykf.testtask.model.CustomerCreateUpdateRequest;
import alexeykf.testtask.model.CustomerDto;
import alexeykf.testtask.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity(CustomerCreateUpdateRequest from);

    CustomerDto toDto(CustomerEntity from);
}
