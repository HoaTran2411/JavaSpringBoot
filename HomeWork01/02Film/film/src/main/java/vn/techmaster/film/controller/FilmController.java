package vn.techmaster.film.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.film.model.Film;

@Controller
@RequestMapping(value = "/")
public class FilmController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/films")
    public String listFilms(Model model) {
        List<Film> films = List.of(
                new Film("Gone with the Wind", "Victor Fleming, David O. Selznick", 1939),
                new Film("Bố Già", "Trấn Thành", 2020), 
                new Film("Parasite", "Bong Joon-ho", 2019),
                new Film("Money Heist", "Álex Pina", 2018)
        );
        model.addAttribute("films", films);
        return "film";
    }
}
