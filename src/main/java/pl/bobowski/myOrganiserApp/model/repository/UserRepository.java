package pl.bobowski.myOrganiserApp.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE login =?1")
    Optional<UserEntity> getUserByLogin(String login);

    List<UserEntity> findAll();
}
