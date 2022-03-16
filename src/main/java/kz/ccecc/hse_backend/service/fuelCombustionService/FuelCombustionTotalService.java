package kz.ccecc.hse_backend.service.fuelCombustionService;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionTotalDataDto;

import java.util.List;

public interface FuelCombustionTotalService {
    List<FuelCombustionTotalDataDto> getFuelCombustionTotal(Long year);
}
