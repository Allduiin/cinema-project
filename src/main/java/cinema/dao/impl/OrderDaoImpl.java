package cinema.dao.impl;

import cinema.dao.OrderDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.Order;
import cinema.model.User;
import cinema.util.HibernateUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order add(Order order) {
        return new EntityManager<Order>().add(order);
    }

    @Override
    public List<Order> getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
