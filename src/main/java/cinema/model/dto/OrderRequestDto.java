package cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull
    Long userId;
}
