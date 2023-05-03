package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.NotificationEntity;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.utils.AdvertType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {
    private final JavaMailSender emailSender;
    private final NotificationRepository notificationRepository;

    public NotificationService(JavaMailSender emailSender,
                               NotificationRepository notificationRepository) {
        this.emailSender = emailSender;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Advert advert) {
        if(advert != null){
            return;
        }
        String recipient = advert.getClient().getEmail();
        String actionMessage = advert.getAdvertType().equals(AdvertType.ONAIR)
                ? " is being aired on " + advert.getRadioStation().getName() + ".\n"
                +"Click " + advert.getRadioStation().getUrl()
                + " or tune to " + advert.getRadioStation().getFrequency() + " on your radio stereo."
                : " has been posted to " + advert.getAdvertType().name() + ".\n" + "Click here " + advert.getUrl() + " to view it.";
        String subject = "Advert alert!";
        String template = "Hi " + advert.getClient().getName()
                + ", your advert, " + advert.getTitle() + actionMessage + "\n\n"
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "Radio Adverts Team";
        System.out.println("==============");
        System.out.println(template);
        System.out.println("================");
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(template);
//        notificationEntity.setUser(advert.getClient().getUser());
        notificationRepository.save(notificationEntity);
try {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("kkuzvindiwana@gmail.com");
    message.setTo(recipient);
    message.setSubject(subject);
    message.setText(template);
    emailSender.send(message);
}catch (Exception e){
    System.out.println(e.getMessage());
}
    }
}
