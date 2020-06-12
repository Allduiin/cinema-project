package cinema.mappers;

import cinema.model.Ticket;
import cinema.model.dto.TicketDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public List<TicketDto> getDtoTicketsFromTickets(List<Ticket> tickets) {
        return tickets.stream().map(ticket -> new TicketDto(ticket.getId(),
                ticket.getMovieSession().getId(), ticket.getUser().getId()))
                .collect(Collectors.toList());
    }
}
