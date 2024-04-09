package hh.sof3.cinemacircle.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface MovieListRepository extends CrudRepository<MovieList, Long>{

    // peritään findAll(), finsId(), deleteById(), save();

    List<MovieList> findByName(String name);

}
