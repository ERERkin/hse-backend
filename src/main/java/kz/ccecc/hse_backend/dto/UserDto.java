package kz.ccecc.hse_backend.dto;

import kz.ccecc.hse_backend.dto.base.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UserDto extends AbstractDto {
    private String login;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private Boolean gender;
    private String urlImg;
    private UserRoleDto userRole;

    public UserDto(String login, String password, String email, String fullName, String phone, Boolean gender, String urlImg, UserRoleDto userRole) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.urlImg = urlImg;
        this.userRole = userRole;
    }

    public UserDto(String login, String password, String fullName, UserRoleDto userRole) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
    }

    public UserDto() {
    }
}
