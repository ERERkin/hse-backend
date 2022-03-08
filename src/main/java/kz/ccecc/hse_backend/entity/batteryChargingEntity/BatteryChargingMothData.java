package kz.ccecc.hse_backend.entity.batteryChargingEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder
@Entity
@Table(name = "battery_charging_month_data_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatteryChargingMothData extends AbstractEntity {
    @Column(name = "month")
    LocalDate month;
    @Column(name = "work_time")
    Double workTime;
    @Column(name = "battery_count")
    Long batteryCount;
    @Column(name = "battery_capacity")
    Double batteryCapacity;
    @ManyToOne(targetEntity = BatteryChargingYearLimit.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "year_limit_id")
    BatteryChargingYearLimit yearLimit;
}
