package kz.ccecc.hse_backend.entity.fuelCombustionEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingPollutionSource;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "fuel_combustion_year_limits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelCombustionYearLimit extends AbstractEntity {
    @Column(name = "year")
    Long year;
    @Column(name = "work_time")
    BigDecimal workTime;
    @Column(name = "consumption_m3_on_year")
    BigDecimal consumptionM3OnYear;
    @Column(name = "consumption_kg_on_year")
    BigDecimal consumptionKgOnYear;
    @Column(name = "consumption_ton_on_year")
    BigDecimal consumptionTonOnYear;
    @ManyToOne(targetEntity = FuelCombustionFuelType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type_id")
    FuelCombustionFuelType fuelType;
    @OneToMany(mappedBy = "yearLimit", fetch = FetchType.EAGER)
    List<FuelCombustionMothData> mothDataList;
}
