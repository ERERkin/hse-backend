package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRMothData;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRMothDataDtoMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRMothDataRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRMothDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TechnicalEquipmentSPRMothDataServiceImpl extends AbstractService<TechnicalEquipmentSPRMothData,
        TechnicalEquipmentSPRMothDataDto,
        TechnicalEquipmentSPRMothDataRepository,
        TechnicalEquipmentSPRMothDataDtoMapper> implements TechnicalEquipmentSPRMothDataService {
    public TechnicalEquipmentSPRMothDataServiceImpl(TechnicalEquipmentSPRMothDataRepository repository, TechnicalEquipmentSPRMothDataDtoMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRMothData");
    }
}
