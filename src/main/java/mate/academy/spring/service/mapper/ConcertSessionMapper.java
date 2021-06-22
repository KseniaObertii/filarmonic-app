package mate.academy.spring.service.mapper;

import java.time.LocalDateTime;
import mate.academy.spring.dao.StageDao;
import mate.academy.spring.model.ConcertSession;
import mate.academy.spring.model.dto.ConcertSessionRequestDto;
import mate.academy.spring.model.dto.ConcertSessionResponseDto;
import mate.academy.spring.service.ConcertService;
import org.springframework.stereotype.Component;

@Component
public class ConcertSessionMapper {
    private ConcertService concertService;
    private StageDao stageDao;

    public ConcertSessionMapper(ConcertService concertService, StageDao stageDao) {
        this.concertService = concertService;
        this.stageDao = stageDao;
    }

    public ConcertSessionResponseDto mapToDto(ConcertSession concertSession) {
        ConcertSessionResponseDto concertSessionResponseDto = new ConcertSessionResponseDto();
        concertSessionResponseDto.setId(concertSession.getId());
        concertSessionResponseDto.setConcertId(concertSession.getConcert().getId());
        concertSessionResponseDto.setStageId(concertSession.getStage().getId());
        concertSessionResponseDto.setShowTime(concertSession.getShowTime().toString());
        return concertSessionResponseDto;
    }

    public ConcertSession mapToModel(ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = new ConcertSession();
        concertSession.setConcert(concertService.get(
                concertSessionRequestDto.getConcertId())); //.get() ?????
        concertSession.setStage(stageDao.get(
                concertSessionRequestDto.getStageId()).get());
        concertSession.setShowTime(LocalDateTime.parse(concertSessionRequestDto.getShowTime()));
        return concertSession;
    }
}
