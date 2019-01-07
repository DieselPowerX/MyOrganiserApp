package pl.bobowski.myOrganiserApp.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

    final
    UserSession userSession;

    @Autowired
    public LoginInterceptor(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!request.getRequestURI().contains("login")
        && !request.getRequestURI().contains("registry")
        && !userSession.isLogin()){
            response.sendRedirect("/login");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
