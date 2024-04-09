package hh.sof3.cinemacircle.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieList;
import hh.sof3.cinemacircle.domain.MovieRepository;
import hh.sof3.cinemacircle.domain.StreamingServiceRepository;
import jakarta.validation.Valid;

@Controller
public class MovieControllers {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    // Adds New Movie

        @GetMapping(value = "/addnewmovie")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
        public String addNewMovie(Model model) {

            model.addAttribute("movie", new Movie());
            model.addAttribute("services", streamingServiceRepository.findAll());

            return "NewMovieForm"; //NewMovieForm.html
        }

    // Saves Movie

        @PostMapping(value = "/save")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
        public String saveMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, @RequestParam(name = "selectedServices", required = false) List<Long> selectedServicesIds, Model model) {

            if (bindingResult.hasErrors()) { 
                model.addAttribute("movie", movie);
                model.addAttribute("services", streamingServiceRepository.findAll());
                return "newMovieForm";
            }

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

        @GetMapping(value = "/moviedetails/{id}")
        public String openMovie(@PathVariable("id") Long id, Model model) {

            model.addAttribute("movie", movieRepository.findById(id).orElse(null));

            return "moviedetails"; //moviedetails.html
        }
        
        
    
    // Edits Movie

        @GetMapping(value = "/editmovie/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String editMovie(@PathVariable("id") Long id, Model model) {

            model.addAttribute("services", streamingServiceRepository.findAll());
            model.addAttribute("movie", movieRepository.findById(id));

            return "editmovie"; //editmovie.html
        }

    // Deletes Movie

        @GetMapping(value = "/deletemovie/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
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
}
