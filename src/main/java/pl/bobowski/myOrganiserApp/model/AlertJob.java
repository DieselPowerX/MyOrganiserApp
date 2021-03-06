package pl.bobowski.myOrganiserApp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.bobowski.myOrganiserApp.model.entities.NoteEntity;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import pl.bobowski.myOrganiserApp.model.services.NoteService;
import pl.bobowski.myOrganiserApp.model.services.SmsService;
import pl.bobowski.myOrganiserApp.model.services.UserService;
import java.time.LocalDate;

@Component
public class AlertJob {

    final
    private NoteService noteService;
    private SmsService smsService;
    private UserService userService;

    @Autowired
    public AlertJob(NoteService noteService, SmsService smsService, UserService userService) {
        this.noteService = noteService;
        this.smsService = smsService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    private void getListOfUsers(){
        for (UserEntity user : userService.getAllUsers()) {
            getAllNotesToSendFromUser(user);
        }
    }

    private void getAllNotesToSendFromUser(UserEntity user) {

        for (NoteEntity note : noteService.getAllNotes(user.getId())) {
            if(note.getDueDate().isEqual(LocalDate.now())){
                sendMessages(note, user);
            }
        }

    }

    private void sendMessages(NoteEntity note, UserEntity user) {
        smsService.sendSms(note, user);
    }
}
