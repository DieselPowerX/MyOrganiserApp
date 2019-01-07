package pl.bobowski.myOrganiserApp.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bobowski.myOrganiserApp.model.entities.NoteEntity;
import pl.bobowski.myOrganiserApp.model.forms.NoteForm;
import pl.bobowski.myOrganiserApp.model.repository.NoteRepository;

import java.time.LocalDate;
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

    public List<NoteEntity> getAllNotes(int userId) {
        return sortedList(noteRepository.findByUser_Id(userId));

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
