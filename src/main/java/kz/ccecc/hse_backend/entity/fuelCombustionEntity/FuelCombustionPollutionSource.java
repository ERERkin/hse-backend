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
@Table(name = "fuel_combustion_pollution_sources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelCombustionPollutionSource extends AbstractEntity {
    @Column(name = "name")
    String name;
    @Column(name = "number")
    String number;
    @ManyToOne(targetEntity = FuelCombustionProduction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id")
    FuelCombustionProduction production;
    @OneToMany(mappedBy = "pollutionSource", fetch = FetchType.EAGER)
    List<FuelCombustionFuelType> fuelTypes;
}
