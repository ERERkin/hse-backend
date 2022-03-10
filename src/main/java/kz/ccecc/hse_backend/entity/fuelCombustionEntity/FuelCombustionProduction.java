package kz.ccecc.hse_backend.entity.fuelCombustionEntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "fuel_combustion_productions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelCombustionProduction extends AbstractEntity {
    @Column(name = "name")
    String name;
    @OneToMany(mappedBy = "production", fetch = FetchType.EAGER)
    List<FuelCombustionPollutionSource> pollutionSources;
}
