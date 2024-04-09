package hh.sof3.cinemacircle.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieList;
import hh.sof3.cinemacircle.domain.MovieListRepository;
import hh.sof3.cinemacircle.domain.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@Controller
public class RestControllers {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    // RESTful services for movie entity

        @GetMapping(value = "/movies")
        public @ResponseBody List<Movie> findAllMovies() {
            return (List<Movie>) movieRepository.findAll();
        }

        @GetMapping(value = "/movie/{id}")
        public @ResponseBody Movie bookById(@PathVariable("id") Long movieid) {
            return movieRepository.findById(movieid).orElse(null);
        }

        @PostMapping(value = "/savemovie")
        public @ResponseBody Movie saveMovie(@RequestBody Movie movie) {
            return movieRepository.save(movie);
        }

    //RESTful services for movielists

        @GetMapping(value = "/movielists")
        public @ResponseBody List<MovieList> findAllMovieLists() {
            return (List<MovieList>) movieListRepository.findAll();
        }

        
}
