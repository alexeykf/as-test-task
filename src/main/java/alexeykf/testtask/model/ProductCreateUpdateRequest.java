package alexeykf.testtask.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Value
@JsonDeserialize(builder = ProductCreateUpdateRequest.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
public class ProductCreateUpdateRequest {

    @Size(min = 1, max = 255)
    @NotEmpty
    String title;

    @Size(max = 1024)
    String description;

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 10, fraction = 2)
    BigDecimal price;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
