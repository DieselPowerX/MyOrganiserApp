package pl.bobowski.myOrganiserApp.model.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private UserEntity someUser;

    @BeforeEach
    void setUp(){
        someUser = new UserEntity("test","123","test","123","123");
    }

    @AfterEach
    void removeEntity(){
        userRepository.delete(someUser);
    }

    @Test
    void givenUser_ExistsByLogin_ThenRemoveUser(){
        userRepository.save(someUser);
        assertTrue(userRepository.existsByLogin(someUser.getLogin()));
    }

    @Test
    void givenUser_FindByLogin_ThenRemoveUser(){
        userRepository.save(someUser);
        assertTrue(userRepository.getUserByLogin(someUser.getLogin()).isPresent());
    }

    @Test
    void givenUser_FindUserList_ThenRemoveUser(){
        userRepository.save(someUser);
        assertTrue(userRepository.findAll().size()>0);
    }
}