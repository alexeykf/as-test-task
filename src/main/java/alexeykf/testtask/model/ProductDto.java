package alexeykf.testtask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    BigDecimal price;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
