package kz.ccecc.hse_backend.entity.fuelCombustionEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.batteryChargingEntity.BatteryChargingYearLimit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@Entity
@Table(name = "fuel_combustion_month_data_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelCombustionMothData extends AbstractEntity {
    @Column(name = "month")
    LocalDate month;
    @Column(name = "work_time")
    BigDecimal workTime;
    @Column(name = "consumption_m3_on_month")
    BigDecimal consumptionM3OnMonth;
    @Column(name = "consumption_Liter_on_month")
    BigDecimal consumptionLiterOnMonth;
    @Column(name = "consumption_kg_on_month")
    BigDecimal consumptionKgOnMonth;
    @Column(name = "consumption_ton_on_month")
    BigDecimal consumptionTonOnMonth;
    @ManyToOne(targetEntity = FuelCombustionYearLimit.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "year_limit_id")
    FuelCombustionYearLimit yearLimit;
}
