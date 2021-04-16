package alexeykf.testtask.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Value
@JsonDeserialize(builder = CustomerCreateUpdateRequest.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
public class CustomerCreateUpdateRequest {

    @Size(min = 1, max = 255)
    @NotEmpty
    String title;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
