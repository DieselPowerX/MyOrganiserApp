package pl.robertozog.notesWithWeather.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    private String city;
    @Column(name="post_code")
    private String postCode;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<NoteEntity> userNote;
}
