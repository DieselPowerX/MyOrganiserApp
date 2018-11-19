package pl.robertozog.notesWithWeather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.robertozog.notesWithWeather.model.UserSession;
import pl.robertozog.notesWithWeather.model.forms.NoteForm;
import pl.robertozog.notesWithWeather.model.services.NoteService;
import pl.robertozog.notesWithWeather.model.services.UserService;

@Controller
public class NoteController {

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

        model.addAttribute("noteList", noteService.getAllNotes(userSession.getUserEntity().getId()));
        model.addAttribute("note", new NoteForm());
        return "dashboard";
    }

    @PostMapping("/user/dashboard/addnote")
    public String addNote(@ModelAttribute NoteForm noteForm){

        noteService.addNote(noteForm,userSession.getUserEntity());
        return"redirect:/user/dashboard";
    }
}
