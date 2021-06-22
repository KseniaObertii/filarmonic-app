package mate.academy.spring.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.spring.model.ConcertSession;

public interface ConcertSessionService {
    List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date);

    ConcertSession get(Long id);

    ConcertSession add(ConcertSession session);

    ConcertSession update(ConcertSession concertSession);

    void delete(Long id);
}
