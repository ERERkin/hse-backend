package kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionFuelType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "technical_equipment_spr_year_limits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalEquipmentSPRYearLimit extends AbstractEntity {
    @Column(name = "year")
    Long year;
    @Column(name = "count")
    String count;
    @Column(name = "volume")
    BigDecimal volume;
    @ManyToOne(targetEntity = FuelCombustionFuelType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type_id")
    TechnicalEquipmentSPRFuelType fuelType;
    @OneToMany(mappedBy = "yearLimit", fetch = FetchType.EAGER)
    List<TechnicalEquipmentSPRMothData> mothDataList;
}
