package pl.robertozog.notesWithWeather.model.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {


}
