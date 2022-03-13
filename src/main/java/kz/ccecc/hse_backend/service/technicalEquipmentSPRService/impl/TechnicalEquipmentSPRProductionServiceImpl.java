package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRProductionDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRProduction;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRProductionMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRProductionRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRProductionService;
import org.springframework.stereotype.Service;

@Service
public class TechnicalEquipmentSPRProductionServiceImpl extends AbstractService<TechnicalEquipmentSPRProduction,
        TechnicalEquipmentSPRProductionDto,
        TechnicalEquipmentSPRProductionRepository,
        TechnicalEquipmentSPRProductionMapper> implements TechnicalEquipmentSPRProductionService {
    public TechnicalEquipmentSPRProductionServiceImpl(TechnicalEquipmentSPRProductionRepository repository, TechnicalEquipmentSPRProductionMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRProduction");
    }
}
