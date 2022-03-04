package kz.ccecc.hse_backend.service;

import kz.ccecc.hse_backend.dto.UserDto;
import kz.ccecc.hse_backend.entity.User;
import kz.ccecc.hse_backend.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserDto> {
    UserDto getById(Long id);
    UserDto create(UserDto user);
    UserDto getByLogin(String login);
}
