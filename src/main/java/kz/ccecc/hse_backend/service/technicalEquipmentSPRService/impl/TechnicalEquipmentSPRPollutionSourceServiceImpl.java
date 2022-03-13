package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRPollutionSourceMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRPollutionSourceRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRPollutionSourceService;
import org.springframework.stereotype.Service;

@Service
public class TechnicalEquipmentSPRPollutionSourceServiceImpl extends AbstractService<TechnicalEquipmentSPRPollutionSource,
        TechnicalEquipmentSPRPollutionSourceDto,
        TechnicalEquipmentSPRPollutionSourceRepository,
        TechnicalEquipmentSPRPollutionSourceMapper> implements TechnicalEquipmentSPRPollutionSourceService {
    public TechnicalEquipmentSPRPollutionSourceServiceImpl(TechnicalEquipmentSPRPollutionSourceRepository repository, TechnicalEquipmentSPRPollutionSourceMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRPollutionSource");
    }
}
