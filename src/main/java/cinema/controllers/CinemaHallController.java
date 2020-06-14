package cinema.controllers;

import cinema.mappers.CinemaHallMapper;
import cinema.model.dto.CinemaHallRequestDto;
import cinema.model.dto.CinemaHallResponseDto;
import cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;
    @Autowired
    private CinemaHallMapper cinemaHallMapper;

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto requestDto) {
        cinemaHallService.add(cinemaHallMapper.getCinemaHallFromCinemaHallRequestDto(requestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHall -> cinemaHallMapper
                        .getCinemaHallResponseDtoFromCinemaHall(cinemaHall))
                .collect(Collectors.toList());
    }
}
