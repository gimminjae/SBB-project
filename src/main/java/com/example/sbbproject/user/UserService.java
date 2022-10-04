package com.example.sbbproject.user;

import com.example.sbbproject.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public SiteUser getUser(String name) {
        Optional<SiteUser> optionalSiteUser = userRepository.findByUsername(name);
        if(optionalSiteUser.isPresent()) {
            return optionalSiteUser.get();
        } else {
            throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }
}
