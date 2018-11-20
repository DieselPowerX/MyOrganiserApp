package pl.robertozog.notesWithWeather.model.forms;

import lombok.Data;

@Data
public class UserForm {
    private String login;
    private String password;
    private String passwordRepeat;
    private String city;
    private String postCode;
}
