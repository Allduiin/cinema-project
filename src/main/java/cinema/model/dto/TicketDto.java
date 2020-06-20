package cinema.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private Long id;
    private Long movieSessionId;
    private Long userId;
}
