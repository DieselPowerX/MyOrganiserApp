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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(UserForm user){

        if(checkPasswordRepeat(user)) {
            UserEntity userEntity = new UserEntity(user.getLogin(),user.getPassword(),user.getCity(),user.getPostCode());
            userRepository.save(userEntity);
        }


    }

    private boolean checkPasswordRepeat(UserForm user) {
        return true;
    }
}
