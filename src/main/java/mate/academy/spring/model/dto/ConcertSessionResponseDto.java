package mate.academy.spring.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertSessionResponseDto {
    private Long id;
    private Long concertId;
    private Long stageId;
    private String showTime;
}
