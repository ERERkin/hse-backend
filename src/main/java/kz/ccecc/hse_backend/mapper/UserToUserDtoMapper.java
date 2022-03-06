package kz.ccecc.hse_backend.mapper;

import kz.ccecc.hse_backend.dto.UserDto;
import kz.ccecc.hse_backend.entity.User;
import kz.ccecc.hse_backend.mapper.base.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("modelMapper")
public class UserToUserDtoMapper extends AbstractMapper<User, UserDto> {
    public UserToUserDtoMapper(ModelMapper mapper) {
        super(mapper, User.class, UserDto.class);
    }
}
