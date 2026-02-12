package org.example; // Asigură-te că acest pachet reflectă folderul tău din src/main/java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // Adnotarea e aici!
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @GetMapping("/") // Mapăm rădăcina
    public String index() {
        return "<h1>Erasmus Hub Online!</h1><p>Daca vezi asta, Spring Boot si Docker comunica perfect.</p>";
    }
    
    @GetMapping("/test")
    public String test() {
        return "Endpoint-ul /test functioneaza!";
    }
}