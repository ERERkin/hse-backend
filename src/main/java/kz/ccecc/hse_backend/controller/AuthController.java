package kz.ccecc.hse_backend.controller;

import kz.ccecc.hse_backend.dto.UserAuthRequest;
import kz.ccecc.hse_backend.dto.UserDto;
import kz.ccecc.hse_backend.entity.User;
import kz.ccecc.hse_backend.service.UserService;
import kz.ccecc.hse_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public String getToken(@RequestBody UserAuthRequest user) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
//        } catch (Exception e) {
//            throw new Exception("Auth failed");
//        }
        return "Bearer " + jwtUtil.generateToken(user.getLogin());
    }

    @PostMapping("/sign-up")
    public UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }
}