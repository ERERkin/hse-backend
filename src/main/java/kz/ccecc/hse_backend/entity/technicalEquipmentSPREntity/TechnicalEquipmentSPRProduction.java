package kz.ccecc.hse_backend.entity.technicalEquipmentSPREntity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "technical_equipment_spr_productions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalEquipmentSPRProduction extends AbstractEntity {
    @Column(name = "name")
    String name;
    @OneToMany(mappedBy = "production", fetch = FetchType.EAGER)
    List<TechnicalEquipmentSPRPollutionSource> pollutionSources;
}
