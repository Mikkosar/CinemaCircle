package hh.sof3.cinemacircle.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StreamingServiceRepository extends CrudRepository<StreamingService,Long>{

    // peritään findAll(), finsId(), deleteById(), save();

    List<StreamingService> findByServicename(String servicename);
}
