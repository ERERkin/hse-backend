package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRPollutionSourceDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRPollutionSource;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRPollutionSourceMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRPollutionSourceRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRFuelTypeService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRPollutionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TechnicalEquipmentSPRPollutionSourceServiceImpl extends AbstractService<TechnicalEquipmentSPRPollutionSource,
        TechnicalEquipmentSPRPollutionSourceDto,
        TechnicalEquipmentSPRPollutionSourceRepository,
        TechnicalEquipmentSPRPollutionSourceMapper> implements TechnicalEquipmentSPRPollutionSourceService {
    public TechnicalEquipmentSPRPollutionSourceServiceImpl(TechnicalEquipmentSPRPollutionSourceRepository repository, TechnicalEquipmentSPRPollutionSourceMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRPollutionSource");
    }

    @Autowired
    TechnicalEquipmentSPRFuelTypeService technicalEquipmentSPRFuelTypeService;

    @Override
    public TechnicalEquipmentSPRPollutionSourceDto save(TechnicalEquipmentSPRPollutionSourceDto item) {
        TechnicalEquipmentSPRPollutionSourceDto pollutionSourceSaved = super.save(item);
        if (Objects.isNull(item.getFuelTypes())) return pollutionSourceSaved;
        List<TechnicalEquipmentSPRFuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        item.getFuelTypes().forEach(fuelTypeDto -> {
            fuelTypeDto.setPollutionSource(pollutionSourceSaved);
            TechnicalEquipmentSPRFuelTypeDto fuelTypeSaved =
                    technicalEquipmentSPRFuelTypeService.save(fuelTypeDto);
            fuelTypeSaved.setPollutionSource(null);
            fuelTypeDtoList.add(fuelTypeSaved);
        });
        pollutionSourceSaved.setFuelTypes(fuelTypeDtoList);
        return pollutionSourceSaved;
    }
}
