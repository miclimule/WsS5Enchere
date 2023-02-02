package com.example.demo.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class FCMNotificationService {

    private static final String FIREBASE_CONFIG_PATH = "firebase-service-account.json";

    public static void sendNotification(String token, String title, String body) throws IOException, FirebaseMessagingException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream()))
                .build();
        FirebaseApp.initializeApp(options,"notifd");
        Message message = Message.builder().setToken(token)
                .putData("title", title)
                .putData("body", body)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Sent message: " + response);
    }
}
