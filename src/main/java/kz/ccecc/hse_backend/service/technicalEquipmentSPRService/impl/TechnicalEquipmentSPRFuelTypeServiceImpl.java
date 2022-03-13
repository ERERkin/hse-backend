package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRFuelType;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRFuelTypeMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRFuelTypeRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRFuelTypeService;
import org.springframework.stereotype.Service;

@Service
public class TechnicalEquipmentSPRFuelTypeServiceImpl extends AbstractService<TechnicalEquipmentSPRFuelType,
        TechnicalEquipmentSPRFuelTypeDto,
        TechnicalEquipmentSPRFuelTypeRepository,
        TechnicalEquipmentSPRFuelTypeMapper> implements TechnicalEquipmentSPRFuelTypeService {
    public TechnicalEquipmentSPRFuelTypeServiceImpl(TechnicalEquipmentSPRFuelTypeRepository repository, TechnicalEquipmentSPRFuelTypeMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRFuelType");
    }
}
