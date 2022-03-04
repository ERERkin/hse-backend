package kz.ccecc.hse_backend.service.base;

import kz.ccecc.hse_backend.dto.ObjectNotFoundException;
import kz.ccecc.hse_backend.dto.base.AbstractDto;
import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import kz.ccecc.hse_backend.mapper.base.BaseMapper;
import kz.ccecc.hse_backend.repository.BaseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractService <
        E extends AbstractEntity,
        D extends AbstractDto,
        R extends BaseRepository<E>,
        M extends BaseMapper<E, D>>
        implements BaseService<D> {
    protected final R repository;
    protected final M mapper;
    protected final String entityName;

    public AbstractService(R repository, M mapper, String entityName) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityName = entityName;
    }

    @Override
    public D getById(Long id) {
        E entity = repository.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Couldn't find " + entityName +" with id " + id));;
        return mapper.toDto(entity);
    }

    @Override
    public D save(D item) {
        E entity = mapper.toEntity(item);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<D> getAll() {
        List<E> eList = repository.findAll().stream().map(x -> (E) x).collect(Collectors.toList());
        return mapper.toDtos(eList);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
