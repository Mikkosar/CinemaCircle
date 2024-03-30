package hh.sof3.cinemacircle.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import hh.sof3.cinemacircle.domain.User;
import hh.sof3.cinemacircle.domain.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class HomePageController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private UserRepository userRepository;

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
    public String saveMovie(@ModelAttribute Movie movie, @RequestParam(name = "selectedServices", required = false) List<Long> selectedServicesIds) {

        if (selectedServicesIds == null) {

            movieRepository.save(movie);

            return "redirect:/home";
        }

        for (Long id : selectedServicesIds) {
            movie.inServices(streamingServiceRepository.findById(id).orElse(null));
        }

        movieRepository.save(movie);

        return "redirect:/home";
    }

    @GetMapping(value = "/newcollection")
    public String newCollection(Model model) {

        model.addAttribute("collection", new MovieList());
        model.addAttribute("movies", movieRepository.findAll());

        return "newcollectionform"; //newcollectionform.html
    }

    @GetMapping(value = "/editcollection/{id}")
    public String editCollection(@PathVariable("id") Long id, Model model) {

        model.addAttribute("collection", movieListRepository.findById(id));
        model.addAttribute("movies", movieRepository.findAll());

        return "editcollection"; //editcollection.html
    }
    

    @PostMapping(value = "/savecollection")
    public String saveNewCollection(@ModelAttribute MovieList movieList, @RequestParam(name = "selectedMovies", required = false) List<Long> selectedMoviesIds) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUserName);
        movieList.setUser(currentUser);

        if (selectedMoviesIds == null) {

            movieListRepository.save(movieList);

            return "redirect:/collections";
        }

        for (Long id : selectedMoviesIds) {
            movieList.hasMovie(movieRepository.findById(id).orElse(null));
        }

        movieListRepository.save(movieList);

        return "redirect:/collections";
    }

    @GetMapping(value = "/deletemovie/{id}")
    public String deleteMovie(@PathVariable("id") Long id){

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        
        if (optionalMovie.isPresent()) {
         Movie movieToDelete = optionalMovie.get();

        for (MovieList movieList : movieToDelete.getMovieLists()) {
            movieList.getMovies().remove(movieToDelete);
        }

        movieRepository.delete(movieToDelete);
    }

        return "redirect:/home";
    }

    @GetMapping(value = "/editmovie/{id}")
    public String editMovie(@PathVariable("id") Long id, Model model) {

        model.addAttribute("services", streamingServiceRepository.findAll());
        model.addAttribute("movie", movieRepository.findById(id));

        return "editmovie"; //editmovie.html
    }

    @GetMapping()
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login"; // login.html
    }
}
