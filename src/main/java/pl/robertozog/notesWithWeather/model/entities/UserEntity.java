package pl.robertozog.notesWithWeather.model.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    private String city;
    @Column(name = "post_code")
    private String postCode;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoteEntity> userNote;

    public UserEntity(int id) {
        this.id = id;
    }

    public UserEntity(String login, String password, String city, String postCode) {
        this.login = login;
        this.city = city;
        this.password = password;
        this.postCode = postCode;
    }

    public UserEntity() {
    }
}
