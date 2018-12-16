package pl.bobowski.myOrganiserApp.model.forms;

import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class NoteForm {
    private String title;
    private String message;
    private LocalDate dueDate;
    private int priority;

    public void setDueDate(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
