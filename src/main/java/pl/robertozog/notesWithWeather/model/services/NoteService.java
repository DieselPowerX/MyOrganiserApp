package pl.robertozog.notesWithWeather.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.notesWithWeather.model.entities.NoteEntity;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;
import pl.robertozog.notesWithWeather.model.forms.NoteForm;
import pl.robertozog.notesWithWeather.model.repository.NoteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    final
    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void addNote(NoteForm noteForm, int userId) {
        NoteEntity newNote = new NoteEntity(noteForm.getTitle(),
                noteForm.getMessage(),
                noteForm.getDueDate(),
                noteForm.getPriority(),
                userId);

        noteRepository.save(newNote);
    }

    public void deleteNote(int id) {
        noteRepository.deleteById(id);
    }

    public List<NoteEntity> getAllNotes(int id) {
        return sortedList(noteRepository.findByUser_Id(id));

    }

    private List<NoteEntity> sortedList(List<NoteEntity> notesList) {
        return notesList
                .stream()
                .sorted((s, s1) -> Integer.compare(s.getPriority(), s1.getPriority()) * -1)
                .collect(Collectors.toList());
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
