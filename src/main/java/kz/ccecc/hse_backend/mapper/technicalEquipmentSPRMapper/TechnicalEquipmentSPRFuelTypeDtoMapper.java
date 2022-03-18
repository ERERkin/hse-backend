package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRFuelTypeDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRFuelType;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("modelMapper")
public class TechnicalEquipmentSPRFuelTypeDtoMapper
        extends AbstractMapper<TechnicalEquipmentSPRFuelType, TechnicalEquipmentSPRFuelTypeDto> {
    public TechnicalEquipmentSPRFuelTypeDtoMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRFuelType.class, TechnicalEquipmentSPRFuelTypeDto.class);
    }

    @Autowired
    TechnicalEquipmentSPRYearLimitDtoMapper technicalEquipmentSPRYearLimitDtoMapper;

    @Override
    public TechnicalEquipmentSPRFuelTypeDto toDto(TechnicalEquipmentSPRFuelType entity) {
        List<TechnicalEquipmentSPRYearLimitDto> yearLimitDtoList =
                technicalEquipmentSPRYearLimitDtoMapper.toDtos(entity.getYearLimits());
        TechnicalEquipmentSPRFuelTypeDto fuelTypeDto = super.toDto(entity);
        fuelTypeDto.setYearLimits(yearLimitDtoList);
        return fuelTypeDto;
    }
}
