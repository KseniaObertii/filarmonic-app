package mate.academy.spring.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ConcertSessionRequestDto {
    private Long concertId;
    private Long stageId;
    private String showTime;
}
