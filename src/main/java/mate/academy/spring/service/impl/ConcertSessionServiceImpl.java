package mate.academy.spring.service.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.spring.dao.ConcertSessionDao;
import mate.academy.spring.model.ConcertSession;
import mate.academy.spring.service.ConcertSessionService;
import org.springframework.stereotype.Service;

@Service
public class ConcertSessionServiceImpl implements ConcertSessionService {
    private final ConcertSessionDao sessionDao;

    public ConcertSessionServiceImpl(ConcertSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        return sessionDao.findAvailableSessions(concertId, date);
    }

    @Override
    public ConcertSession get(Long id) {
        return sessionDao.get(id).get();
    }

    @Override
    public ConcertSession add(ConcertSession session) {
        return sessionDao.add(session);
    }

    @Override
    public ConcertSession update(ConcertSession concertSession) {
        return sessionDao.update(concertSession);
    }

    @Override
    public void delete(Long id) {
        sessionDao.delete(id);
    }
}
