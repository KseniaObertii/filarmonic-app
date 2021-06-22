package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.dto.ConcertRequestDto;
import mate.academy.spring.model.dto.ConcertResponseDto;
import mate.academy.spring.service.ConcertService;
import mate.academy.spring.service.mapper.ConcertMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private ConcertService concertService;
    private ConcertMapper concertMapper;

    public ConcertController(ConcertService concertService, ConcertMapper concertMapper) {
        this.concertService = concertService;
        this.concertMapper = concertMapper;
    }

    @PostMapping
    public ConcertResponseDto add(@RequestBody ConcertRequestDto concertRequestDto) {
        return concertMapper.mapToDto(concertService.add(
                concertMapper.mapToModel(concertRequestDto)));
    }

    @GetMapping
    public List<ConcertResponseDto> getAll() {
        return concertService.getAll().stream()
                .map(concertMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
