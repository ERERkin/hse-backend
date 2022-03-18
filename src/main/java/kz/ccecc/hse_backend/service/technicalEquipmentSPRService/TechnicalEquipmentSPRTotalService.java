package kz.ccecc.hse_backend.service.technicalEquipmentSPRService;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRTotalDataResponse;

public interface TechnicalEquipmentSPRTotalService {
    TechnicalEquipmentSPRTotalDataResponse getTechnicalEquipmentSPRTotal(Long year);
}
