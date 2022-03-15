package kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.entity.fuelCombustionEntity.FuelCombustionProduction;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "technical_equipment_spr_pollution_sources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalEquipmentSPRPollutionSource extends AbstractEntity {
    @Column(name = "name")
    String name;
    @Column(name = "number")
    String number;
    @ManyToOne(targetEntity = TechnicalEquipmentSPRProduction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "production_id")
    TechnicalEquipmentSPRProduction production;
    @OneToMany(mappedBy = "pollutionSource", fetch = FetchType.EAGER)
    List<TechnicalEquipmentSPRFuelType> fuelTypes;
}
