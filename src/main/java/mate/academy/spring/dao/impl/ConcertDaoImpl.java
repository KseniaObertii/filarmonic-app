package mate.academy.spring.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.ConcertDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Concert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ConcertDaoImpl extends AbstractDao<Concert> implements ConcertDao {
    public ConcertDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Concert> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Concert.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a concert by id: " + id, e);
        }
    }

    @Override
    public List<Concert> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Concert> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Concert.class);
            criteriaQuery.from(Concert.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all concerts", e);
        }
    }
}
