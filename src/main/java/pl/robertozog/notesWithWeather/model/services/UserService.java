package pl.robertozog.notesWithWeather.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;
import pl.robertozog.notesWithWeather.model.forms.UserForm;
import pl.robertozog.notesWithWeather.model.repository.UserRepository;

@Service
public class UserService {

    final
    UserRepository userRepository;
    PasswordHashService passwordHashService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashService passwordHashService) {
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
    }


    public boolean addUser(UserForm user){

        if(checkPasswordRepeat(user)) {
            UserEntity userEntity = new UserEntity(user.getLogin(),passwordHashService.hash(user.getPassword()) ,user.getCity(),user.getPostCode());
            userRepository.save(userEntity);
            return true;
        }return false;
    }

    private boolean checkPasswordRepeat(UserForm user) {
        return user.getPassword().equals(user.getPasswordRepeat());
    }
}
