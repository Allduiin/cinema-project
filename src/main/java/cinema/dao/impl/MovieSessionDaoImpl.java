package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery.from(MovieSession.class);
            Predicate predicateMovieId = criteriaBuilder.equal(root.get("movie"), movieId);
            Predicate predicateDate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.plusDays(1L).atStartOfDay());
            criteriaQuery.where(predicateMovieId, predicateDate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Movie Sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            session.save(movieSession);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding movie", e);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
