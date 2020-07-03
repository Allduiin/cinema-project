package cinema.mappers;

import cinema.model.Ticket;
import cinema.model.dto.TicketDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public List<TicketDto> getDtoTicketsFromTickets(List<Ticket> tickets) {
        return tickets
                .stream()
                .map(ticket -> {
                    TicketDto ticketDto = new TicketDto();
                    ticketDto.setId(ticket.getId());
                    ticketDto.setMovieSessionId(ticket.getMovieSession().getId());
                    ticketDto.setUserId(ticket.getUser().getId());
                    return ticketDto;
                })
                .collect(Collectors.toList());
    }
}
