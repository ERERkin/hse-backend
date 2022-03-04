package kz.ccecc.hse_backend.service;

import kz.ccecc.hse_backend.entity.User;
import kz.ccecc.hse_backend.entity.UserRole;
import kz.ccecc.hse_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        List<UserRole> roles = new ArrayList<>();
        roles.add(user.getUserRole());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }
}