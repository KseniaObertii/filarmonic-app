package mate.academy.spring.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.StageService;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageDaoImpl extends AbstractDao<Stage> implements StageService {
    public StageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Stage> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Stage.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a stage hall by id: " + id, e);
        }
    }

    @Override
    public List<Stage> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Stage> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Stage.class);
            criteriaQuery.from(Stage.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all stage halls", e);
        }
    }
}
