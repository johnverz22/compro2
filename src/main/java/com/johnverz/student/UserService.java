package com.johnverz.student;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    private List<User> users;

    @PostConstruct
    public void init() throws IOException {
        users = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("users.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            User user = new User();
            user.setUsername(parts[0]);
            user.setPassword(parts[1]);
            users.add(user);
        }
    }
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
