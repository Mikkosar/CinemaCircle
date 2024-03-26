package hh.sof3.cinemacircle.domain;

import org.springframework.data.repository.CrudRepository;

public interface StreamingServiceRepository extends CrudRepository<StreamingService,Long>{

    // peritään findAll(), finsId(), deleteById(), save();

}
