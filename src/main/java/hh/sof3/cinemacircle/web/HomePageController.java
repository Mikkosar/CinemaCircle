package hh.sof3.cinemacircle.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HomePageController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(value = "/home")
    public String homePage(Model model) {

        model.addAttribute("movies", movieRepository.findAll());

        return "Home"; // Home.html
    }

    @GetMapping(value = "/collections")
    public String collectionPage() {
        return "Collections"; //Collections.html
    }

    @GetMapping(value = "/add_new_movie")
    public String addNewMovie(Model model) {

        model.addAttribute("movie", new Movie());

        return "NewMovieForm"; //NewMovieForm.html
    }

    @PostMapping(value = "/save")
    public String saveMovie(@ModelAttribute Movie movie) {

        movieRepository.save(movie);

        return "redirect:/home";
    }
    
    
}
