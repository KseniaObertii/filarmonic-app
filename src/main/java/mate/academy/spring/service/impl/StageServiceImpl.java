package mate.academy.spring.service.impl;

import java.util.List;
import mate.academy.spring.dao.StageService;
import mate.academy.spring.model.Stage;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl implements mate.academy.spring.service.StageService {
    private final StageService stageService;

    public StageServiceImpl(StageService stageService) {
        this.stageService = stageService;
    }

    @Override
    public Stage add(Stage stage) {
        return stageService.add(stage);
    }

    @Override
    public Stage get(Long id) {
        return stageService.get(id).get();
    }

    @Override
    public List<Stage> getAll() {
        return stageService.getAll();
    }
}
