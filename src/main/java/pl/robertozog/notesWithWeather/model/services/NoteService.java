package pl.robertozog.notesWithWeather.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.notesWithWeather.model.entities.NoteEntity;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;
import pl.robertozog.notesWithWeather.model.forms.NoteForm;
import pl.robertozog.notesWithWeather.model.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {
    final
    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public void addNote(NoteForm noteForm, int userId){
        NoteEntity newNote = new NoteEntity(noteForm.getTitle(),noteForm.getMessage(),noteForm.getDueDate(),noteForm.getPriority(),userId);

        noteRepository.save(newNote);
    }

    public void deleteNote(int id){
        noteRepository.deleteById(id);
    }

    public List<NoteEntity> getAllNotes(int id){
        System.out.println(id);
       return noteRepository.findByUser_Id(id);
    }
}
