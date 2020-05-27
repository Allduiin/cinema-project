package cinema.dao.impl;

import cinema.dao.ShoppingCartDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return new AddMethod<ShoppingCart>().add(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> query
                    = criteriaBuilder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = query.from(ShoppingCart.class);
            root.fetch("tickets", JoinType.LEFT);
            return session.createQuery(
                    query.where(criteriaBuilder.equal(root.get("user"), user)))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error adding user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("ShoppingCart update error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}