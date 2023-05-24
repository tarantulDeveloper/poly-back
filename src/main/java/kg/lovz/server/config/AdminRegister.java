package kg.lovz.server.config;

import kg.lovz.server.entity.User;
import kg.lovz.server.entity.enums.Roles;
import kg.lovz.server.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminRegister implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("pass"));
            user.setFirstName("Bekzhan");
            user.setLastName("Satiev");
            user.setPhotoUrl("https://m.media-amazon.com/images/M/MV5BN2FmMTEyMzAtMGI4Mi00M2IzLTkzNzgtZDRlMTA5NTk0YjIzXkEyXkFqcGdeQXVyMjMxNTAxNDk@._V1_.jpg");
            user.setRoles(Set.of(Roles.ADMIN));
            userRepository.save(user);
        }

    }
}
