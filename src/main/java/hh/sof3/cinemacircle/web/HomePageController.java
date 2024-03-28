package hh.sof3.cinemacircle.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieList;
import hh.sof3.cinemacircle.domain.MovieListRepository;
import hh.sof3.cinemacircle.domain.MovieRepository;
import hh.sof3.cinemacircle.domain.StreamingService;
import hh.sof3.cinemacircle.domain.StreamingServiceRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HomePageController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    @GetMapping(value = "/home")
    public String homePage(Model model) {

        model.addAttribute("movies", movieRepository.findAll());

        return "Home"; // Home.html
    }

    @GetMapping(value = "/collections")
    public String collectionPage(Model model) {

        model.addAttribute("collections", movieListRepository.findAll());

        return "Collections"; //Collections.html
    }

    @GetMapping(value = "/addnewmovie")
    public String addNewMovie(Model model) {

        model.addAttribute("movie", new Movie());
        model.addAttribute("services", streamingServiceRepository.findAll());

        return "NewMovieForm"; //NewMovieForm.html
    }

    @GetMapping(value = "/opencollection/{id}")
    public String openCollection(@PathVariable("id") Long id, Model model) {

        MovieList movieList = movieListRepository.findById(id).orElse(null);
        List<Movie> movies = movieList.getMovies();

        model.addAttribute("movies", movies);
        model.addAttribute("collection", movieList);

        return "opencollection"; //opencollection.html
    }
    

    @PostMapping(value = "/save")
    public String saveMovie(@ModelAttribute Movie movie) {

        movieRepository.save(movie);

        return "redirect:/home";
    }
    
    
}
