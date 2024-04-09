package hh.sof3.cinemacircle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3.cinemacircle.domain.MovieList;
import hh.sof3.cinemacircle.domain.MovieListRepository;

@DataJpaTest
public class MovieListRepositoryTest {

    //Here we will test MovieListrepos functionality (create, findByName, delete)

    @Autowired
    private MovieListRepository movieListRepository;

    @Test
    public void createMovieList() {
        MovieList list = new MovieList(
            "New List",
            "My New List",
            null);

        movieListRepository.save(list);

        assertThat(list.getListid()).isNotNull();
    }

    @Test
    public void findByName() {
        MovieList list = new MovieList(
            "New List",
            "My New List",
            null);

        movieListRepository.save(list);
        
        List<MovieList> movieLists = movieListRepository.findByName("New List");

        assertThat(movieLists).hasSize(1);
        assertThat(movieLists.get(0).getDesc()).isEqualTo("My New List");
    }

    @Test
    public void deleteList() {
        MovieList list = new MovieList(
            "New List",
            "My New List",
            null);

        movieListRepository.save(list);
        movieListRepository.delete(list);

        assertThat(movieListRepository.findByName("New List")).hasSize(0);
    }
}
