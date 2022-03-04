package kz.ccecc.hse_backend.mapper.base;

import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.base.AbstractEntity;

import java.util.List;

public interface BaseMapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dList);

    List<D> toDtos(List<E> eList);
}
