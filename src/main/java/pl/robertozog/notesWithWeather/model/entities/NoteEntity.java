package pl.robertozog.notesWithWeather.model.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
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




    public NoteEntity(String title, String message, LocalDate dueDate, int priority, int userId){
        this.title = title;
        this.message = message;
        this.dueDate = dueDate;
        this.priority = priority;
        this.user = new UserEntity(userId);
    }

    public NoteEntity(){}


}
