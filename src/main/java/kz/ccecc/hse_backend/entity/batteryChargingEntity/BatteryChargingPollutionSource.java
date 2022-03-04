package kz.ccecc.hse_backend.entity.batteryChargingEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "battery_charging_pollution_sources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatteryChargingPollutionSource extends AbstractEntity {
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "number", nullable = false)
    String number;
    @ManyToOne(targetEntity = BatteryChargingProduction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id")
    BatteryChargingProduction production;
    @OneToMany(mappedBy = "pollutionSource", fetch = FetchType.EAGER)
    List<BatteryChargingYearLimit> yearLimits;
}
