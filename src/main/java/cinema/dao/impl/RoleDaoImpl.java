package cinema.dao.impl;

import cinema.dao.RoleDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends EntityManagerImpl<Role> implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getByRoleName(Role.RoleName roleName) {
        try (Session session = sessionFactory.openSession()) {
            return (Role) session.createQuery("FROM roles where roleName =: roleName")
                    .setParameter("roleName", roleName)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all movies. ", e);
        }
    }
}
