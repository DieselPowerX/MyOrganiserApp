package pl.robertozog.notesWithWeather.model.forms;

import lombok.Data;

@Data
public class NoteForm {
    private String title;
    private String message;
    private String dueDate;
    private int priority;

}
