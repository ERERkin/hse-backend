package kz.ccecc.hse_backend.mapper;

import kz.ccecc.hse_backend.dto.UserRoleDto;
import kz.ccecc.hse_backend.entity.UserRole;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRoleToUserRoleDtoMapper extends AbstractMapper<UserRole, UserRoleDto> {
    public UserRoleToUserRoleDtoMapper(ModelMapper mapper) {
        super(mapper, UserRole.class, UserRoleDto.class);
    }
}
