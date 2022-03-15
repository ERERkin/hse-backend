package kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionPollutionSource;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "technical_equipment_spr_fuel_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalEquipmentSPRFuelType extends AbstractEntity {
    @Column(name = "fuel_type")
    String fuelType;
    @Column(name = "fuel_density")
    BigDecimal fuelDensity;
    @ManyToOne(targetEntity = TechnicalEquipmentSPRPollutionSource.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pollution_source_id")
    TechnicalEquipmentSPRPollutionSource pollutionSource;
    @OneToMany(mappedBy = "fuelType", fetch = FetchType.EAGER)
    List<TechnicalEquipmentSPRYearLimit> yearLimits;
}
