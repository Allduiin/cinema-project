package cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private LocalDateTime time;
    private List<TicketDto> tickets;
    private Long userId;
}
