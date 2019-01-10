package pl.bobowski.myOrganiserApp.model.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    private String login;
    private String password;
    private String passwordRepeat;
    private String city;
    private String postCode;
    private String phoneNumber;
}


