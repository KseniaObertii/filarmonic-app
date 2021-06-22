package mate.academy.spring.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcertRequestDto {
    private String title;
    private String description;
}
