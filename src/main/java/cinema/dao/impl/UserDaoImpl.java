package cinema.dao.impl;

import cinema.dao.UserDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends EntityManagerImpl<User> implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM users where email =: email")
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Error getting Book by title", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
