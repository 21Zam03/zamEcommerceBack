package com.zam.zamMarket.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfig {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("zamecommerce-9293a-firebase-adminsdk-gkjen-0a034495b6.json");

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("zamecommerce-9293a.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }

}
