package cinema.dao.impl;

import cinema.dao.TicketDao;
import cinema.lib.Dao;
import cinema.model.Ticket;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        return new AddMethod<Ticket>().add(ticket);
    }
}
