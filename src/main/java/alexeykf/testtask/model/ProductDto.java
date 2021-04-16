package alexeykf.testtask.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class ProductDto {

    UUID id;
    String title;
    String description;
    BigDecimal price;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
