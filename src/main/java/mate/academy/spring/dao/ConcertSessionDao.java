package mate.academy.spring.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.ConcertSession;

public interface ConcertSessionDao extends GenericDao<ConcertSession> {
    List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date);

    Optional<ConcertSession> get(Long id);

    ConcertSession update(ConcertSession concertSession);

    void delete(Long id);
}
