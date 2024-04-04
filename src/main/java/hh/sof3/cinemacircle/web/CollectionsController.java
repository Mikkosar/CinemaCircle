package hh.sof3.cinemacircle.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieList;
import hh.sof3.cinemacircle.domain.MovieListRepository;
import hh.sof3.cinemacircle.domain.MovieRepository;
import hh.sof3.cinemacircle.domain.User;
import hh.sof3.cinemacircle.domain.UserRepository;

@Controller
public class CollectionsController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    // Collection Page

        @GetMapping(value = "/collections")
        public String collectionPage(Model model) {

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUserName = authentication.getName();
    
                User user = userRepository.findByUsername(currentUserName);
    
                model.addAttribute("currentUser", user);
            }
            
            model.addAttribute("collections", movieListRepository.findAll());

            return "Collections"; //Collections.html
        }

    // Open collection

        @GetMapping(value = "/opencollection/{id}")
        public String openCollection(@PathVariable("id") Long id, Model model) {

            MovieList movieList = movieListRepository.findById(id).orElse(null);
            List<Movie> movies = movieList.getMovies();

            model.addAttribute("movies", movies);
            model.addAttribute("collection", movieList);

            return "opencollection"; //opencollection.html
        }

    // New Collection

        @GetMapping(value = "/newcollection")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
        public String newCollection(Model model) {
    
            model.addAttribute("collection", new MovieList());
            model.addAttribute("movies", movieRepository.findAll());
    
            return "newcollectionform"; //newcollectionform.html
        }

    // Delete Collection

        @GetMapping(value = "/deletecollection/{id}")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
        public String deleteCollection(@PathVariable("id") Long id) {
    
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
    
            User user = userRepository.findByUsername(currentUserName);
    
            MovieList movieList = movieListRepository.findById(id).orElse(null);
            if (movieList != null && movieList.getUser().getUsername().equals(currentUserName) || user.getRole().equals("ADMIN")) {
                movieListRepository.deleteById(id);
            } else {
                return "error";
            }
    
            return "redirect:/collections";
        }

    // Edit Collection

        @GetMapping(value = "/editcollection/{id}")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
        public String editCollection(@PathVariable("id") Long id, Model model) {
    
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
    
            User user = userRepository.findByUsername(currentUserName);
            MovieList movieList = movieListRepository.findById(id).orElse(null);
    
            if (movieList.getUser().getId() == user.getId()) {
                model.addAttribute("collection", movieListRepository.findById(id));
                model.addAttribute("movies", movieRepository.findAll());
        
                return "editcollection"; //editcollection.html
            }
            else {
                return "error";
            }
        }

    // Save Collection

        @PostMapping(value = "/savecollection")
        @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
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

}
