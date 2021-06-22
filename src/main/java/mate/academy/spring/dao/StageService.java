package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.Stage;

public interface StageService extends GenericDao<Stage> {
    Optional<Stage> get(Long id);

    List<Stage> getAll();
}
