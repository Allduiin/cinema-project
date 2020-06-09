package cinema.dao.impl;

import cinema.dao.TicketDao;
import cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        return new EntityManagerImpl<Ticket>(sessionFactory).add(ticket);
    }
}
