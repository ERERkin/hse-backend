package kz.ccecc.hse_backend.service.base;


import kz.ccecc.hse_backend.dto.base.AbstractDto;

import java.util.List;

public interface BaseService<D extends AbstractDto> {
    D getById(Long id);

    D save(D item);

    List<D> getAll();

    void deleteById(Long id);
}
