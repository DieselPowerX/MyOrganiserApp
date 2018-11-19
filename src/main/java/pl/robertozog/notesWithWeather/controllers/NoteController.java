package pl.robertozog.notesWithWeather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.robertozog.notesWithWeather.model.UserSession;
import pl.robertozog.notesWithWeather.model.forms.NoteForm;
import pl.robertozog.notesWithWeather.model.services.NoteService;
import pl.robertozog.notesWithWeather.model.services.UserService;

@Controller
public class NoteController {


    @Value("${api.key}")
    String apiKey;



    final
    UserSession userSession;
    NoteService noteService;

    @Autowired
    public NoteController(UserSession userSession,NoteService noteService) {
        this.userSession = userSession;
        this.noteService = noteService;
    }


    @GetMapping("/user/dashboard")
    public String getDashboard(Model model){

        model.addAttribute("noteList", noteService.getAllNotes(userSession.getId()));
        model.addAttribute("note", new NoteForm());
        model.addAttribute("currentDate", noteService.getCurrentDate());

        return "dashboard";
    }

    @PostMapping("/user/dashboard/addnote")
    public String addNote(@ModelAttribute NoteForm noteForm){
        noteService.addNote(noteForm,userSession.getId());
        return"redirect:/user/dashboard";

    }

    @GetMapping("/user/dashboard/deletenote/{id}")
    public String deleteNote(@PathVariable("id") int id){

        noteService.deleteNote(id);

        return "redirect:/user/dashboard";
    }

}
