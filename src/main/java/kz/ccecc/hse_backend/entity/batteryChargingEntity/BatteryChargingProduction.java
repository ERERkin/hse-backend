package kz.ccecc.hse_backend.entity.batteryChargingEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "battery_charging_productions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatteryChargingProduction extends AbstractEntity {
    @Column(name = "name", nullable = false)
    String name;
    @OneToMany(mappedBy = "production", fetch = FetchType.EAGER)
    List<BatteryChargingPollutionSource> pollutionSources;
}
