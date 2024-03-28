package hh.sof3.cinemacircle.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long>{

    // peritään findAll(), finsId(), deleteById(), save();

    List<Movie> findByName(String title);
}
