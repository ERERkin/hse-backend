package kz.ccecc.hse_backend.dto.base;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class AbstractDto implements Serializable {
    Long id;
}
