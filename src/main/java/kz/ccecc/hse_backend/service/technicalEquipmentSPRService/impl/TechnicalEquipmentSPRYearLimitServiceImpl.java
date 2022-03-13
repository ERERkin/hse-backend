package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRYearLimitMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRYearLimitRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRYearLimitService;
import org.springframework.stereotype.Service;

@Service
public class TechnicalEquipmentSPRYearLimitServiceImpl extends AbstractService<TechnicalEquipmentSPRYearLimit,
        TechnicalEquipmentSPRYearLimitDto,
        TechnicalEquipmentSPRYearLimitRepository,
        TechnicalEquipmentSPRYearLimitMapper> implements TechnicalEquipmentSPRYearLimitService {
    public TechnicalEquipmentSPRYearLimitServiceImpl(TechnicalEquipmentSPRYearLimitRepository repository, TechnicalEquipmentSPRYearLimitMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRYearLimit");
    }
}
