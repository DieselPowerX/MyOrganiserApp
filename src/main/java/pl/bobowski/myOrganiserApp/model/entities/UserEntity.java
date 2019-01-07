package pl.bobowski.myOrganiserApp.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
    private String phone;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoteEntity> userNote;

    public UserEntity(int id) {
        this.id = id;

    }

    public UserEntity(String login, String password, String city, String postCode, String phone) {
        this.login = login;
        this.city = city;
        this.password = password;
        this.postCode = postCode;
        this.phone = phone;
    }
}
