package pl.robertozog.notesWithWeather.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.notesWithWeather.model.UserSession;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;
import pl.robertozog.notesWithWeather.model.forms.UserForm;
import pl.robertozog.notesWithWeather.model.repository.UserRepository;

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


    public boolean addUser(UserForm user){

        if(checkPasswordRepeat(user) && !ifLoginExist(user)) {
            UserEntity userEntity = new UserEntity(user.getLogin(),passwordHashService.hash(user.getPassword()) ,user.getCity(),user.getPostCode());
            userRepository.save(userEntity);
            return true;
        }return false;
    }

    private boolean checkPasswordRepeat(UserForm user) {
        return user.getPassword().equals(user.getPasswordRepeat());
    }

    private boolean ifLoginExist(UserForm user){

        return userRepository.existsByLogin(user.getLogin());
    }
    public boolean tryLogIn(UserForm user) {

        Optional<UserEntity> userOptional = userRepository.getUserByLogin(user.getLogin());
        if (userOptional.isPresent()) {
            if (passwordHashService.matches(user.getPassword(), userOptional.get().getPassword())) {
                userSession.setLogin(true);
                userSession.setUserEntity(userOptional.get());
            }
        }
        return userSession.isLogin();
    }
}

