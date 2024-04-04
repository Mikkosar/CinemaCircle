package hh.sof3.cinemacircle.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieListRepository extends CrudRepository<MovieList, Long>{

    // peritään findAll(), finsId(), deleteById(), save();

    @Modifying
    @Query("DELETE FROM MovieList m WHERE m.listid = :listId AND m.user.id = :userId")
    void deleteByListIdAndUserId(@Param("listId") Long listId, @Param("userId") Long userId);

}
