package mate.academy.spring.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.ConcertSessionDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.ConcertSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ConcertSessionDaoImpl extends AbstractDao<ConcertSession>
        implements ConcertSessionDao {
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);

    public ConcertSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ConcertSession> criteriaQuery =
                    criteriaBuilder.createQuery(ConcertSession.class);
            Root<ConcertSession> root = criteriaQuery.from(ConcertSession.class);
            Predicate concertPredicate = criteriaBuilder.equal(root.get("concert"), concertId);
            Predicate datePredicate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.atTime(END_OF_DAY));
            Predicate allConditions = criteriaBuilder.and(concertPredicate, datePredicate);
            criteriaQuery.select(root).where(allConditions);
            root.fetch("concert");
            root.fetch("stage");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available sessions for concert with id: "
                    + concertId + " for date: " + date, e);
        }
    }

    @Override
    public Optional<ConcertSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(ConcertSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a concert session by id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(get(id).get());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete concert session by id "
                    + id + " from DB.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ConcertSession update(ConcertSession concertSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(concertSession);
            transaction.commit();
            return concertSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update concert session " + concertSession
                    + " from DB.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
