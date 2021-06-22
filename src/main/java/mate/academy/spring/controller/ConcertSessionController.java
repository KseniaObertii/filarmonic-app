package mate.academy.spring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.ConcertSession;
import mate.academy.spring.model.dto.ConcertSessionRequestDto;
import mate.academy.spring.model.dto.ConcertSessionResponseDto;
import mate.academy.spring.service.ConcertSessionService;
import mate.academy.spring.service.mapper.ConcertSessionMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert-sessions")
public class ConcertSessionController {
    private ConcertSessionMapper concertSessionMapper;
    private ConcertSessionService concertSessionService;

    public ConcertSessionController(ConcertSessionMapper concertSessionMapper,
                                    ConcertSessionService concertSessionService) {
        this.concertSessionMapper = concertSessionMapper;
        this.concertSessionService = concertSessionService;
    }

    @GetMapping("/available")
    public List<ConcertSessionResponseDto> getAvailableConcertSessions(
            @RequestParam Long concertId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return concertSessionService.findAvailableSessions(concertId, date).stream()
                .map(concertSessionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ConcertSessionResponseDto add(
            @RequestBody ConcertSessionRequestDto concertSessionRequestDto) {
        return concertSessionMapper.mapToDto(concertSessionService.add(
                        concertSessionMapper.mapToModel(concertSessionRequestDto)));
    }

    @PutMapping("/{id}")
    public ConcertSessionResponseDto update(
            @PathVariable Long id,
            @RequestBody ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = concertSessionMapper.mapToModel(concertSessionRequestDto);
        concertSession.setId(id);
        return concertSessionMapper.mapToDto(concertSessionService.update(concertSession));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        concertSessionService.delete(id);
    }
}
