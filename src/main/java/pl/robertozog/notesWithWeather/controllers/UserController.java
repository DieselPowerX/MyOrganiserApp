package pl.robertozog.notesWithWeather.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.robertozog.notesWithWeather.model.forms.UserForm;
import pl.robertozog.notesWithWeather.model.services.UserService;

@Controller
public class UserController {


    final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showMenu(Model model){
        model.addAttribute("logUser", new UserForm());
        model.addAttribute("regUser", new UserForm());
        return "menu";
    }

    @PostMapping("/user/registry")
    public String regUser(@ModelAttribute("regUser") UserForm user, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("matchesPassword", userService.addUser(user));
        return "redirect:/";
    }

    @PostMapping("/user/login")
    public String logUser(@ModelAttribute("logUser") UserForm user){




        return "redirect:/";
    }


}
