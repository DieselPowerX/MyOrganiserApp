package pl.bobowski.myOrganiserApp.model.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import pl.bobowski.myOrganiserApp.model.forms.UserForm;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    UserEntity someUser;

    @BeforeEach
    void setUp(){
        someUser = new UserEntity("test","$2a$10$4ZO9Vg26r6amhBMGgNokDucFgfWGCIuyiLAVmdYd.M/OnWdzTym8a","test","123","123");
        userService.userRepository.save(someUser);
    }

    @AfterEach
    void removeEntity(){
        userService.userRepository.delete(someUser);
    }

    @Test
    void ifFindingTheListOfUsers(){
        assertNotNull(userService.getAllUsers());
    }

    @Test
    void ifResetUserSession(){
        userService.userSession.setLoginUser("test");
        userService.userSession.setLogin(true);
        userService.resetSession();
        assertFalse(userService.userSession.isLogin());
    }

    @Test
    void ifGettingProperErrors(){
        UserForm user1 = new UserForm("","","","","","");
        assertEquals("Data incomplite",userService.regErrors(user1));

        UserForm user2 = new UserForm("test1","123","321","test","123","123");
        assertEquals("Password do not mach",userService.regErrors(user2));

        UserForm user3 = new UserForm("test1","123","123","test","123","123");
        assertEquals("Data correct",userService.regErrors(user3));

        UserForm user4 = new UserForm("test","123","123","test","123","123");
        assertEquals("Login exist",userService.regErrors(user4));
    }

    @Test
    void ifAddingUsers(){
        UserForm user1 = new UserForm("test1","123","123","test","123","123");
        userService.addUser(user1);
        assertTrue(userService.userRepository.existsByLogin(user1.getLogin()));
        userService.userRepository.delete(userService.userRepository.getUserByLogin(user1.getLogin()).get());
    }

    @Test
    void ifCanLogIn(){
        UserForm user = new UserForm("test","123","123","test","123","123");
        assertTrue(userService.tryLogIn(user));
    }

    @Test
    void ifGettingProperLogErrors(){
        UserForm user = new UserForm("test","","","test","123","123");
        assertEquals("Fill in the fields", userService.logErrors(user));

        UserForm user1 = new UserForm("","123","123","test","123","123");
        assertEquals("Fill in the fields", userService.logErrors(user1));

        UserForm user2 = new UserForm("test","123","123","test","123","123");
        assertEquals("Login or password do not mach", userService.logErrors(user2));

    }
}