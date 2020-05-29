package cinema.dao.impl;

import cinema.exceptions.DataProcessingException;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntityManager<T> {
    public T add(T t) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding object", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
