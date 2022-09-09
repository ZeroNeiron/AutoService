package autoservice.controller;

import autoservice.dto.request.RepairmanRequestDto;
import autoservice.dto.response.OrderResponseDto;
import autoservice.dto.response.RepairmanResponseDto;
import autoservice.model.Repairman;
import autoservice.service.RepairmanService;
import autoservice.service.SalaryService;
import autoservice.service.mapper.OrderMapper;
import autoservice.service.mapper.RepairmanMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repairmans")
public class RepairmanController {
    private final RepairmanService repairmanService;
    private final RepairmanMapper repairmanMapper;
    private final OrderMapper orderMapper;

    private final SalaryService salaryService;

    public RepairmanController(RepairmanService repairmanService,
                               RepairmanMapper repairmanMapper,
                               OrderMapper orderMapper,
                               SalaryService salaryService) {
        this.repairmanService = repairmanService;
        this.repairmanMapper = repairmanMapper;
        this.orderMapper = orderMapper;
        this.salaryService = salaryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RepairmanResponseDto createRepairman(@Valid @RequestBody
                                                    RepairmanRequestDto repairmanRequestDto) {
        return repairmanMapper
                .mapToDto(repairmanService
                        .create(repairmanMapper
                                .mapToModel(repairmanRequestDto)));
    }

    @PutMapping("/{id}")
    public RepairmanResponseDto updateRepairman(@PathVariable Long id,
                                      @Valid @RequestBody RepairmanRequestDto repairmanRequestDto) {
        Repairman repairman = repairmanMapper.mapToModel(repairmanRequestDto);
        repairman.setId(id);
        return repairmanMapper.mapToDto(repairmanService.create(repairman));
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getByCompletedOrdersById(@PathVariable Long id) {
        return repairmanService.getById(id).getCompletedOrders().stream()
                .distinct()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{repairmanId}/orders/{orderId}/salary")
    public BigDecimal getRepairmanSalaryByOrderId(@PathVariable("repairmanId") Long repairmanId,
                                                  @PathVariable("orderId") Long orderId) {
        return salaryService.getSalaryByRepairmanIdAndOrderId(repairmanId, orderId);
    }
}
