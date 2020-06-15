package cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequestAddDto {
    @NotNull
    private String title;
    private String description;
}
