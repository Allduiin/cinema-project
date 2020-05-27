package cinema.dao.impl;

import cinema.dao.UserDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.User;
import cinema.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        return new EntityManager<User>().add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
