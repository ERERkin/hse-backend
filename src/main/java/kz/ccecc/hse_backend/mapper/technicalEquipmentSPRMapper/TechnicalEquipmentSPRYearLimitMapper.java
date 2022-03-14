package kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnicalEquipmentSPRYearLimitMapper
        extends AbstractMapper<TechnicalEquipmentSPRYearLimit, TechnicalEquipmentSPRYearLimitDto> {
    public TechnicalEquipmentSPRYearLimitMapper(ModelMapper mapper) {
        super(mapper, TechnicalEquipmentSPRYearLimit.class, TechnicalEquipmentSPRYearLimitDto.class);
    }

    @Autowired
    TechnicalEquipmentSPRMothDataMapper technicalEquipmentSPRMothDataMapper;

    @Override
    public TechnicalEquipmentSPRYearLimitDto toDto(TechnicalEquipmentSPRYearLimit entity) {
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList =
                technicalEquipmentSPRMothDataMapper.toDtos(entity.getMothDataList());
        TechnicalEquipmentSPRYearLimitDto yearLimitDto = super.toDto(entity);
        yearLimitDto.setMothDataList(mothDataDtoList);
        return yearLimitDto;
    }
}
