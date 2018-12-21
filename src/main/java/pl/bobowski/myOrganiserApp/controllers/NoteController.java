package pl.bobowski.myOrganiserApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.bobowski.myOrganiserApp.model.UserSession;
import pl.bobowski.myOrganiserApp.model.forms.NoteForm;
import pl.bobowski.myOrganiserApp.model.services.NoteService;
import pl.bobowski.myOrganiserApp.model.services.WeatherService;

@Controller
public class NoteController {

    @Value("${api.key}")
    String apiKey;

    final private UserSession userSession;
    private NoteService noteService;
    private WeatherService weatherService;

    @Autowired
    public NoteController(UserSession userSession, NoteService noteService, WeatherService weatherService) {
        this.userSession = userSession;
        this.noteService = noteService;
        this.weatherService = weatherService;
    }



    @GetMapping("/{user}/dashboard")
    public String getDashboard(Model model){

        model.addAttribute("noteList", noteService.getAllNotes(userSession.getId()));
        model.addAttribute("note", new NoteForm());
        model.addAttribute("currentDate", noteService.getCurrentDate());
        model.addAttribute("weather", weatherService.loadWeather(userSession.getCity(), apiKey));
        model.addAttribute("city", userSession.getCity());
        return "dashboard";
    }

    @PostMapping("/user/dashboard/addnote")
    public String addNote(@ModelAttribute NoteForm noteForm) {
        noteService.addNote(noteForm, userSession.getId());
        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/dashboard/deletenote/{id}")
    public String deleteNote(@PathVariable("id") int id) {
        noteService.deleteNote(id);
        return "redirect:/user/dashboard";
    }
}
