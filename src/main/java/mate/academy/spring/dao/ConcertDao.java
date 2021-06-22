package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.Concert;

public interface ConcertDao extends GenericDao<Concert> {
    Optional<Concert> get(Long id);

    List<Concert> getAll();
}
