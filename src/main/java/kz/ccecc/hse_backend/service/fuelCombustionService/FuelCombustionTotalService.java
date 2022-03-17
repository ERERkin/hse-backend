package kz.ccecc.hse_backend.service.fuelCombustionService;

import kz.ccecc.hse_backend.dto.fuelCombustionDto.FuelCombustionTotalDataResponse;

import java.util.List;

public interface FuelCombustionTotalService {
    List<FuelCombustionTotalDataResponse> getFuelCombustionTotal(Long year);
}
