package kz.ccecc.hse_backend.entity.batteryChargingEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "battery_charging_year_limits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatteryChargingYearLimit extends AbstractEntity {
    @Column(name = "battery_model")
    String batteryModel;
    @Column(name = "work_time")
    Double workTime;
    @Column(name = "battery_count")
    Long batteryCount;
    @Column(name = "battery_apacity")
    Double batteryCapacity;
    @ManyToOne(targetEntity = BatteryChargingPollutionSource.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pollution_source_id")
    BatteryChargingPollutionSource pollutionSource;
    @OneToMany(mappedBy = "yearLimit", fetch = FetchType.EAGER)
    List<BatteryChargingMothData> mothDataList;
}
