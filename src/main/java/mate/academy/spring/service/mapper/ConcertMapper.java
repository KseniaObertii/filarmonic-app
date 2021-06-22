package mate.academy.spring.service.mapper;

import mate.academy.spring.model.Concert;
import mate.academy.spring.model.dto.ConcertRequestDto;
import mate.academy.spring.model.dto.ConcertResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {
    public ConcertResponseDto mapToDto(Concert concert) {
        ConcertResponseDto concertResponseDto = new ConcertResponseDto();
        concertResponseDto.setId(concert.getId());
        concertResponseDto.setTitle(concert.getTitle());
        concertResponseDto.setDescription(concert.getDescription());
        return concertResponseDto;
    }

    public Concert mapToModel(ConcertRequestDto concertRequestDto) {
        Concert concert = new Concert();
        concert.setTitle(concertRequestDto.getTitle());
        concert.setDescription(concertRequestDto.getDescription());
        return concert;
    }
}
