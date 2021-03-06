package pl.bobowski.myOrganiserApp.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(name = "due_date")
    private LocalDate dueDate;
    private String message;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public NoteEntity(String title, String message, LocalDate dueDate, int priority, int userId) {
        this.title = title;
        this.message = message;
        this.dueDate = dueDate;
        this.priority = priority;
        this.user = new UserEntity(userId);
    }

    public NoteEntity(String title, String message, LocalDate dueDate, int priority, UserEntity userEntity) {
        this.title = title;
        this.message = message;
        this.dueDate = dueDate;
        this.priority = priority;
        this.user = userEntity;
    }

}
