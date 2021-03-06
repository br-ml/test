package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entities.UserEntity;
import bg.softuni.mobilele.model.entities.UserRoleEntity;
import bg.softuni.mobilele.model.entities.enums.UserRoleEnum;
import bg.softuni.mobilele.model.security.CurrentUser;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           CurrentUser currentUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean authenticate(String userName, String password) {
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(userName);

        if (userEntityOpt.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userEntityOpt.get().getPassword());
        }
    }

    @Override
    public void loginUser(String userName) {

        UserEntity user = userRepository.findByUsername(userName).orElseThrow();

        List<UserRoleEnum> userRoles = user.
                getUserRoles().
                stream().
                map(UserRoleEntity::getRole).
                collect(Collectors.toList());

        currentUser.
                setAnonymous(false).
                setName(user.getUsername()).
                setUserRoles(userRoles);
    }

    @Override
    public void logoutCurrentUser() {
        currentUser.setAnonymous(true);
    }
}
