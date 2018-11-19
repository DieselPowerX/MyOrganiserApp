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


    public void addNote(NoteForm noteForm, UserEntity user){
        NoteEntity newNote = new NoteEntity(noteForm.getTitle(),noteForm.getMessage(),noteForm.getDueDate(),noteForm.getPriority(),user);
        noteRepository.save(newNote);
    }

    public List<NoteEntity> getAllNotes(int id){
       return noteRepository.findByUser_Id(id);
    }
}
