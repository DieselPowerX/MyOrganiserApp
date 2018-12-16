package pl.bobowski.myOrganiserApp.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bobowski.myOrganiserApp.model.UserSession;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import pl.bobowski.myOrganiserApp.model.forms.UserForm;
import pl.bobowski.myOrganiserApp.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final
    UserRepository userRepository;
    PasswordHashService passwordHashService;
    UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashService passwordHashService, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
        this.userSession = userSession;
    }

    public void addUser(UserForm user) {

        UserEntity userEntity = new UserEntity(user.getLogin(),
                passwordHashService.hash(user.getPassword()),
                user.getCity(),
                user.getPostCode());

        userRepository.save(userEntity);
    }

    public String regErrors(UserForm user) {

        if (user.getLogin().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getPasswordRepeat().isEmpty() ||
                user.getCity().isEmpty() ||
                user.getPostCode().isEmpty()) {
            return "Data incomplite";
        }
        if (!checkPasswordRepeat(user)) {
            return "Password do not mach";
        }
        if (ifLoginExist(user)) {
            return "Login exist";
        }

        return "Data correct";
    }

    public String logErrors(UserForm user) {
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return "Login or password do not mach";
        }
        return "Logged in";
    }

    private boolean checkPasswordRepeat(UserForm user) {
        return user.getPassword().equals(user.getPasswordRepeat());
    }

    private boolean ifLoginExist(UserForm user) {

        return userRepository.existsByLogin(user.getLogin());
    }

    public boolean tryLogIn(UserForm user) {

        Optional<UserEntity> userOptional = userRepository.getUserByLogin(user.getLogin());
        if (userOptional.isPresent()) {
            if (passwordHashService.matches(user.getPassword(), userOptional.get().getPassword())) {
                userSession.setLogin(true);
                userSession.setLoginUser(userOptional.get().getLogin());
                userSession.setId(userOptional.get().getId());
                userSession.setCity(userOptional.get().getCity());
            }
        }
        return userSession.isLogin();
    }

    public void resetSession() {
        userSession.setLoginUser(null);
        userSession.setLogin(false);
        userSession.setLoginUser(null);
    }
}

