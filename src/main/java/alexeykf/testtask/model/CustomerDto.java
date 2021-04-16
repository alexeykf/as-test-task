package alexeykf.testtask.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class CustomerDto {

    UUID id;
    String title;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
