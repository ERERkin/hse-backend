package kz.ccecc.hse_backend.service.impl;

import kz.ccecc.hse_backend.dto.UserRoleDto;
import kz.ccecc.hse_backend.entity.UserRole;
import kz.ccecc.hse_backend.mapper.UserRoleToUserRoleDtoMapper;
import kz.ccecc.hse_backend.repository.UserRoleRepository;
import kz.ccecc.hse_backend.service.UserRoleService;
import kz.ccecc.hse_backend.service.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends AbstractService<UserRole, UserRoleDto, UserRoleRepository, UserRoleToUserRoleDtoMapper> implements UserRoleService {
    public UserRoleServiceImpl(UserRoleRepository repository, UserRoleToUserRoleDtoMapper mapper) {
        super(repository, mapper, "userRole");
    }
}