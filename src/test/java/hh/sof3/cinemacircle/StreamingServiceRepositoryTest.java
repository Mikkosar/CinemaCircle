package hh.sof3.cinemacircle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3.cinemacircle.domain.StreamingService;
import hh.sof3.cinemacircle.domain.StreamingServiceRepository;

@DataJpaTest
public class StreamingServiceRepositoryTest {

    //Here we will test StreamingServiverepos functionality (create, findByName, delete)

    @Autowired
    private StreamingServiceRepository serviceRepository;

    @Test
    public void createService() {
        StreamingService service = new StreamingService(
           "Viaplay" 
        );
        serviceRepository.save(service);

        assertThat(service.getServiceId()).isNotNull();
    }

    @Test
    public void findByName() {
        StreamingService service = new StreamingService(
            "Viaplay" 
        );
        serviceRepository.save(service);

        List<StreamingService> services = serviceRepository.findByServicename("Viaplay");

        assertThat(services).hasSize(1);
        assertThat(services.get(0).getServiceName()).isEqualTo("Viaplay");
    }

    @Test
    public void deleteService() {
        StreamingService service = new StreamingService(
            "Viaplay" 
        );

        serviceRepository.save(service);
        serviceRepository.delete(service);

        assertThat(serviceRepository.findByServicename("Viaplay")).hasSize(0);
    }
}
