package hh.sof3.cinemacircle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieRepository;

@DataJpaTest
public class MovieRepositoryTest {

    //Here we will test Movierepos functionality (create, findByName, delete)

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void createMovie() {
        Movie movie = new Movie(
            "new movie",
            "horro",
            "new horror movie",
            "Mikko",
            "120"
            );
        movieRepository.save(movie);

        assertThat(movie.getMovieId()).isNotNull();
    }

    @Test
    public void findByName() {
        List<Movie> movie = movieRepository.findByName("Shining");

        assertThat(movie).hasSize(1);
        assertThat(movie.get(0).getDirector()).isEqualTo("Stanley Kubrick");
    }

    @Test
    public void deleteMovie() {
        Movie movie = new Movie(
            "new movie",
            "horro",
            "new horror movie",
            "Mikko",
            "120"
            );

        movieRepository.save(movie);
        movieRepository.delete(movie);

        assertThat(movieRepository.findByName("new movie")).hasSize(0);
    }
}
