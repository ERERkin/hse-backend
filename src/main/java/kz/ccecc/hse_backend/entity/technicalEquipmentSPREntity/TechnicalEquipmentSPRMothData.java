package kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionYearLimit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@Entity
@Table(name = "technical_equipment_spr_month_data_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalEquipmentSPRMothData extends AbstractEntity {
    @Column(name = "month")
    LocalDate month;
    @Column(name = "count")
    String count;
    @Column(name = "volume")
    BigDecimal volume;
    @ManyToOne(targetEntity = FuelCombustionYearLimit.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "year_limit_id")
    TechnicalEquipmentSPRYearLimit yearLimit;
}
