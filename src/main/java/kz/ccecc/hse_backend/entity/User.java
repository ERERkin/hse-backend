package kz.ccecc.hse_backend.entity;

import kz.ccecc.hse_backend.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity {
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_phone")
    private String phone;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "url_img")
    private String urlImg;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;
}