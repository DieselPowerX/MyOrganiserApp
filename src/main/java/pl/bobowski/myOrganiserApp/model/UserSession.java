package pl.bobowski.myOrganiserApp.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSession {

    private boolean isLogin;
    private int id;
    private String loginUser;
    private String city;
    private String phone;
}
