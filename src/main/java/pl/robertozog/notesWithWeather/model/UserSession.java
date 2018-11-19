package pl.robertozog.notesWithWeather.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.robertozog.notesWithWeather.model.entities.UserEntity;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSession {

        private boolean isLogin;
        private UserEntity userEntity;


}
