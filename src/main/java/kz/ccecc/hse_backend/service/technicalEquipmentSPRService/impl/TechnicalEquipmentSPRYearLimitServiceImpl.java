package kz.ccecc.hse_backend.service.technicalEquipmentSPRService.impl;

import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRMothDataDto;
import kz.ccecc.hse_backend.dto.technicalEquipmentSPRDto.TechnicalEquipmentSPRYearLimitDto;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRMothData;
import kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity.TechnicalEquipmentSPRYearLimit;
import kz.ccecc.hse_backend.mapper.technicalEquipmentSPRMapper.TechnicalEquipmentSPRYearLimitMapper;
import kz.ccecc.hse_backend.repository.technicalEquipmentSPRRepository.TechnicalEquipmentSPRYearLimitRepository;
import kz.ccecc.hse_backend.service.base.AbstractService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRMothDataService;
import kz.ccecc.hse_backend.service.technicalEquipmentSPRService.TechnicalEquipmentSPRYearLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TechnicalEquipmentSPRYearLimitServiceImpl extends AbstractService<TechnicalEquipmentSPRYearLimit,
        TechnicalEquipmentSPRYearLimitDto,
        TechnicalEquipmentSPRYearLimitRepository,
        TechnicalEquipmentSPRYearLimitMapper> implements TechnicalEquipmentSPRYearLimitService {
    public TechnicalEquipmentSPRYearLimitServiceImpl(TechnicalEquipmentSPRYearLimitRepository repository, TechnicalEquipmentSPRYearLimitMapper mapper) {
        super(repository, mapper, "technicalEquipmentSPRYearLimit");
    }

    @Autowired
    TechnicalEquipmentSPRMothDataService technicalEquipmentSPRMothDataService;

    @Override
    public TechnicalEquipmentSPRYearLimitDto save(TechnicalEquipmentSPRYearLimitDto item) {
        TechnicalEquipmentSPRYearLimitDto yearLimitSaved = super.save(item);
        if(Objects.isNull(item.getMothDataList())) return yearLimitSaved;
        List<TechnicalEquipmentSPRMothDataDto> mothDataDtoList = new ArrayList<>();
        item.getMothDataList().forEach(mothDataDto -> {
            mothDataDto.setYearLimit(yearLimitSaved);
            TechnicalEquipmentSPRMothDataDto mothDataSaved =
                    technicalEquipmentSPRMothDataService.save(mothDataDto);
            mothDataSaved.setYearLimit(null);
            mothDataDtoList.add(mothDataDto);
        });
        yearLimitSaved.setMothDataList(mothDataDtoList);
        return yearLimitSaved;
    }
}
