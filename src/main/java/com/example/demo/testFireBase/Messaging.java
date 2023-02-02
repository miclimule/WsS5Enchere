package com.example.demo.testFireBase;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

public class Messaging {

	
	public Messaging() {
		super();
	}

	public static FirebaseMessaging firebaseMessaging() {
		try {
			GoogleCredentials googleCredentials = GoogleCredentials
		            .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		    FirebaseOptions firebaseOptions = FirebaseOptions
		            .builder()
		            .setCredentials(googleCredentials)
		            .build();
		    FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
		    return FirebaseMessaging.getInstance(app);
		} catch (Exception e) {
			return null;
		}
	    
	}
	
}
