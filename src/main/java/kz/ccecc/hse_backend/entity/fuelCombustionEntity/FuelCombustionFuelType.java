package kz.ccecc.hse_backend.entity.fuelCombustionEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingProduction;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "fuel_combustion_fuel_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelCombustionFuelType extends AbstractEntity {
    @Column(name = "fuel_type")
    String fuelType;
    @Column(name = "fuel_category")
    String fuelCategory;
    @Column(name = "fuel_density")
    BigDecimal fuelDensity;
    @ManyToOne(targetEntity = FuelCombustionPollutionSource.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pollution_source_id")
    FuelCombustionPollutionSource pollutionSource;
    @OneToMany(mappedBy = "fuelType", fetch = FetchType.EAGER)
    List<FuelCombustionYearLimit> yearLimits;
}
