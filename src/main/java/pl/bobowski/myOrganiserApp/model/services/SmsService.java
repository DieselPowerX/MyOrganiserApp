package pl.bobowski.myOrganiserApp.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bobowski.myOrganiserApp.model.UserSession;
import pl.bobowski.myOrganiserApp.model.entities.NoteEntity;
import pl.bobowski.myOrganiserApp.model.entities.UserEntity;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

@Service
public class SmsService {

    final
    UserSession userSession;

    @Autowired
    public SmsService(UserSession userSession) {
        this.userSession = userSession;
    }

    public void sendSms(NoteEntity noteEntity, UserEntity userEntity) {


        try {

            OAuthClient client = new OAuthClient("JjoOfl8HKmwv82MF3n7mDgnhAectLMPUWVpRTJyL");
            SmsFactory smsApi = new SmsFactory(client);
            String phoneNumber = userEntity.getPhone();
            SMSSend action = smsApi.actionSend()
                    .setText("Czesc: " + userEntity.getLogin() + " Wydarzenie: " + noteEntity.getTitle() + " zostało zapisane na dzień: " + noteEntity.getDueDate())
                    .setTo(phoneNumber);

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList()) {
                System.out.println(status.getNumber() + " " + status.getStatus());
            }
        } catch (
                ClientException e) {
            e.printStackTrace();
        } catch (
                SmsapiException e) {
            e.printStackTrace();
        }catch (NoClassDefFoundError e){
            System.out.println("do nothing");
        }
    }
}
