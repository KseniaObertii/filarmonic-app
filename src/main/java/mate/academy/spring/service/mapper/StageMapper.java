package mate.academy.spring.service.mapper;

import mate.academy.spring.model.Stage;
import mate.academy.spring.model.dto.StageRequestDto;
import mate.academy.spring.model.dto.StageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StageMapper {
    public StageResponseDto mapToDto(Stage stage) {
        StageResponseDto stageDto = new StageResponseDto();
        stageDto.setId(stage.getId());
        stageDto.setCapacity(stage.getCapacity());
        stageDto.setDescription(stage.getDescription());
        return stageDto;
    }

    public Stage mapToModel(StageRequestDto stageRequestDto) {
        Stage stage = new Stage();
        stage.setCapacity(stageRequestDto.getCapacity());
        stage.setDescription(stageRequestDto.getDescription());
        return stage;
    }
}
