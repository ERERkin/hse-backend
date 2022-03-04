package kz.ccecc.hse_backend.repository;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}

