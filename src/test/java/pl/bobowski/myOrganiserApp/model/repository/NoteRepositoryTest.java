package pl.bobowski.myOrganiserApp.model.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.bobowski.myOrganiserApp.model.entities.NoteEntity;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

//todo
/*
@SpringBootTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;
    public UserRepository userRepository;
    private NoteEntity someNote;
    private UserEntity someUser;

    @BeforeEach
    void setUp(){
        someNote = new NoteEntity("test","test", LocalDate.now(),1,userRepository.findAll().stream().findFirst().get());
    }

    @AfterEach
    void removeEntity(){

    }

    @Test
    void givenUser_ExistsByLogin_ThenRemoveUser(){
        noteRepository.save(someNote);
        assertTrue(1==1);
    }
}*/
