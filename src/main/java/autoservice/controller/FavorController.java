package autoservice.controller;

import autoservice.dto.request.FavorRequestDto;
import autoservice.dto.response.FavorResponseDto;
import autoservice.model.Favor;
import autoservice.service.FavorService;
import autoservice.service.StatusService;
import autoservice.service.mapper.FavorMapper;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favors")
public class FavorController {

    private final FavorService favorService;
    private final FavorMapper favorMapper;
    private final StatusService statusService;

    public FavorController(FavorService favorService,
                           FavorMapper favorMapper,
                           StatusService statusService) {
        this.favorService = favorService;
        this.favorMapper = favorMapper;
        this.statusService = statusService;
    }

    @PutMapping("/{id}")
    public FavorResponseDto updateFavor(@PathVariable Long id,
                                       @Valid @RequestBody FavorRequestDto favorRequestDto) {
        Favor favor = favorMapper.mapToModel(favorRequestDto);
        favor.setId(id);
        return favorMapper.mapToDto(favorService.create(favor));
    }

    @PutMapping("/{id}/status")
    public FavorResponseDto updateFavorStatus(@PathVariable Long id,
                                              @RequestParam String newStatus) {
        return favorMapper
                .mapToDto(favorService
                        .create(statusService
                                .changeFavorStatus(id, Favor.Status.valueOf(newStatus))));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavorResponseDto createFavor(@Valid @RequestBody FavorRequestDto favorRequestDto) {
        return favorMapper
                .mapToDto(favorService
                        .create(favorMapper.mapToModel(favorRequestDto)));
    }
}
