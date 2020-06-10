package cinema.dao.impl;

import cinema.dao.OrderDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.Order;
import cinema.model.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order add(Order order) {
        return new EntityManagerImpl<Order>(sessionFactory).add(order);
    }

    @Override
    public List<Order> getByUser(User user) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> query
                    = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            root.fetch("tickets", JoinType.LEFT);
            return session.createQuery(
                    query.where(criteriaBuilder.equal(root.get("user"), user)))
                    .getResultStream().distinct().collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataProcessingException("Error taking order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
