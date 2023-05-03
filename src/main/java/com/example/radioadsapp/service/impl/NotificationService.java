package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.NotificationEntity;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.utils.AdvertType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final JavaMailSender emailSender;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(JavaMailSender emailSender,
                               NotificationRepository notificationRepository,
                               UserRepository userRepository) {
        this.emailSender = emailSender;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void sendEmailNotification(Advert advert) {
        if (advert == null) {
            return;
        }
        String recipient = advert.getClient().getEmail();
        String actionMessage = advert.getAdvertType().equals(AdvertType.ONAIR)
                ? " is being aired on " + advert.getRadioStation().getName() + ".\n"
                + "Click " + advert.getRadioStation().getUrl()
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
        User user = userRepository.findByEmail(advert.getClient().getCreatedBy());
        notificationEntity.setUser(user);
        notificationRepository.save(notificationEntity);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kkuzvindiwana@gmail.com");
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(template);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void newAdvert(Advert advert) {
        if (advert == null) {
            return;
        }
        String recipient = advert.getRadioStation().getCreatedBy();
        String subject = "New Advert Alert!";
        String template = "Hi " + advert.getRadioStation().getName()
                + ", a new advert, " + advert.getTitle() + "has been created by"+ "\n\n"
                + advert.getClient().getName() + "\n"
                + "Click " + "https://localhost:8080/advert/list to view it \n\n"
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "Radio Adverts Team";
        System.out.println("==============");
        System.out.println(template);
        System.out.println("================");
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(template);
        User user = userRepository.findByEmail(advert.getRadioStation().getCreatedBy());
        notificationEntity.setUser(user);
        notificationRepository.save(notificationEntity);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(advert.getClient().getEmail());
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(template);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void paymentNotification(Payment payment) {
        if (payment == null) {
            return;
        }
        String recipient = payment.getRadioStation().getCreatedBy();
        String subject = "New Payment Alert!";
        String template = "Hi " + payment.getRadioStation().getName()
                + ", you have received  a new payment for , " + payment.getAdvert().getTitle() + "from"+ "\n\n"
                + payment.getClient().getName() + "\n"
                + "Click " + "https://localhost:8080/payments/list to view it \n\n"
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "Radio Adverts Team";
        System.out.println("==============");
        System.out.println(template);
        System.out.println("================");
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(template);
        User user = userRepository.findByEmail(payment.getRadioStation().getCreatedBy());
        notificationEntity.setUser(user);
        notificationRepository.save(notificationEntity);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(payment.getClient().getEmail());
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(template);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
