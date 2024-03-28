package hh.sof3.cinemacircle.domain;

import org.springframework.data.repository.CrudRepository;

public interface MovieListRepository extends CrudRepository<MovieList, Long>{

    // peritään findAll(), finsId(), deleteById(), save();

}
