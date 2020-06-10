package cinema.dao.impl;

import cinema.dao.TicketDao;
import cinema.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends EntityManagerImpl<Ticket> implements TicketDao {
}
