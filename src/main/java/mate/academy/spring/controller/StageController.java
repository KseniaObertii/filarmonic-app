package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dao.StageDao;
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
    private StageDao stageDao;

    public StageController(StageMapper stageMapper,
                           StageDao stageDao) {
        this.stageMapper = stageMapper;
        this.stageDao = stageDao;
    }

    @PostMapping
    public StageResponseDto add(
            @RequestBody StageRequestDto stageRequestDto) {
        return stageMapper.mapToDto(
                stageDao.add(stageMapper.mapToModel(stageRequestDto)));
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageDao.getAll().stream()
                .map(stageMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
