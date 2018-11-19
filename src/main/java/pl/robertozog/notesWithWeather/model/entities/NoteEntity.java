package pl.robertozog.notesWithWeather.model.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(name = "due_date")
    private String dueDate;
    private String message;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;



}
