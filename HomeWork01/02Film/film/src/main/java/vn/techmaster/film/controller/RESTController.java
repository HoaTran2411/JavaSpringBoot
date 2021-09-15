package vn.techmaster.film.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.film.model.Film;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RESTController {
    @GetMapping("/films")
    public ResponseEntity<List<Film>> listBooks() {
        List<Film> films = List.of(
            new Film("Gone with the Wind", "Victor Fleming, David O. Selznick", 1939),
            new Film("Bố Già", "Trấn Thành", 2020), 
            new Film("Parasite", "Bong Joon-ho", 2019),
            new Film("Money Heist", "Álex Pina", 2018)
        );
        return ResponseEntity.ok().body(films);
    }
}
