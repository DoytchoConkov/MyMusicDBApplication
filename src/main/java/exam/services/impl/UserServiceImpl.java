package exam.services.impl;

import exam.models.entities.User;
import exam.models.security.CurrentUser;
import exam.models.service.UserServiceModel;
import exam.repositories.UserRepository;
import exam.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        if (this.userRepository.findByEmail(userServiceModel.getEmail()).isEmpty() &&
                this.userRepository.findByUsername(userServiceModel.getUsername()).isEmpty()) {
            User user = modelMapper.map(userServiceModel, User.class);
            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean haveUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        if (passwordEncoder.matches(password, userOpt.get().getPassword())) {
            currentUser.setUsername(userOpt.get().getUsername());
            currentUser.setAnonymous(false);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
}
