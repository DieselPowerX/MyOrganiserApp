package pl.robertozog.notesWithWeather.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE login =?1")
    Optional<UserEntity> getUserByLogin(String login);
}
