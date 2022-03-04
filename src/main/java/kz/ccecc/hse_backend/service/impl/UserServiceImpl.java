package kz.ccecc.hse_backend.service.impl;

import kz.ccecc.hse_backend.dto.UserDto;
import kz.ccecc.hse_backend.dto.UserRoleDto;
import kz.ccecc.hse_backend.entity.User;
import kz.ccecc.hse_backend.mapper.UserToUserDtoMapper;
import kz.ccecc.hse_backend.repository.UserRepository;
import kz.ccecc.hse_backend.service.UserRoleService;
import kz.ccecc.hse_backend.service.UserService;
import kz.ccecc.hse_backend.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User, UserDto, UserRepository, UserToUserDtoMapper> implements UserService {
    public UserServiceImpl(UserRepository repository, UserToUserDtoMapper mapper) {
        super(repository, mapper, "user");
    }

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDto create(UserDto user) {
        user.setPassword(encoder.encode(user.getPassword()));
        UserRoleDto userRole = userRoleService.getById(1L); //ROLE_USER
        user.setUserRole(userRole);
        return save(user);
    }

    @Override
    public UserDto save(UserDto user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(mapper.toEntity(user)));
    }

    @Override
    public UserDto getByLogin(String login) {
        return mapper.toDto(repository.findByLogin(login));
    }
}