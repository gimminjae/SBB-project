package com.example.sbbproject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);

        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);
        userRepository.save(siteUser);

        return siteUser;
    }
}
