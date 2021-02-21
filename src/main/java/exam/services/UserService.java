package exam.services;

import exam.models.entities.User;
import exam.models.service.UserServiceModel;

public interface UserService {
    boolean registerUser(UserServiceModel user);

    boolean haveUser(String username, String password);

    User findByUsername(String username);
}
