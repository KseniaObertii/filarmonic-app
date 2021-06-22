package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dao.StageService;
import mate.academy.spring.model.dto.StageRequestDto;
import mate.academy.spring.model.dto.StageResponseDto;
import mate.academy.spring.service.mapper.StageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stages")
public class StageController {
    private StageMapper stageMapper;
    private StageService stageService;

    public StageController(StageMapper stageMapper,
                           StageService stageService) {
        this.stageMapper = stageMapper;
        this.stageService = stageService;
    }

    @PostMapping
    public StageResponseDto add(
            @RequestBody StageRequestDto stageRequestDto) {
        return stageMapper.mapToDto(
                stageService.add(stageMapper.mapToModel(stageRequestDto)));
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageService.getAll().stream()
                .map(stageMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
