package cinema.dao.impl;

import cinema.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EntityManagerImpl<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public T add(T t) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
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
