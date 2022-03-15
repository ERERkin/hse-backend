package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRFuelType;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRFuelTypeMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRFuelTypeRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRFuelTypeService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TechnicalEquipmentSPRFuelTypeServiceImpl extends AbstractService<TechnicalEquipmentSPRFuelType,
        TechnicalEquipmentSPRFuelTypeDto,
        TechnicalEquipmentSPRFuelTypeRepository,
        TechnicalEquipmentSPRFuelTypeMapper> implements TechnicalEquipmentSPRFuelTypeService {
    public TechnicalEquipmentSPRFuelTypeServiceImpl(TechnicalEquipmentSPRFuelTypeRepository repository, TechnicalEquipmentSPRFuelTypeMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRFuelType");
    }

    @Autowired
    TechnicalEquipmentSPRYearLimitService technicalEquipmentSPRYearLimitService;

    @Override
    public TechnicalEquipmentSPRFuelTypeDto save(TechnicalEquipmentSPRFuelTypeDto item) {
        TechnicalEquipmentSPRFuelTypeDto fuelTypeSaved = super.save(item);
        if (Objects.isNull(item.getFuelType())) return fuelTypeSaved;
        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList = new ArrayList<>();
        item.getYearLimits().forEach(yearLimitDto -> {
            yearLimitDto.setFuelType(fuelTypeSaved);
            TechnicalEquipmentSPRYearLimitDto yearLimitSaved =
                    technicalEquipmentSPRYearLimitService.save(yearLimitDto);
            yearLimitSaved.setFuelType(null);
            yearLimitDtoList.add(yearLimitSaved);
        });
        fuelTypeSaved.setYearLimits(yearLimitDtoList);
        return fuelTypeSaved;
    }
}
