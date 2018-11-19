package pl.robertozog.notesWithWeather.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.robertozog.notesWithWeather.model.entities.NoteEntity;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Integer> {

    List<NoteEntity> findByUser_Id(int id);



}
