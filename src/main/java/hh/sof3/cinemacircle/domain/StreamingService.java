package hh.sof3.cinemacircle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "streaming_service")
public class StreamingService {

    //Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String serviceName;

    //Constructor

    public StreamingService(String serviceName) {
        super();
        this.serviceName = serviceName;
    }

    //Null Constructor

    public StreamingService() {
        super();
        this.serviceId = null;
        this.serviceName = null;
    }

    //Setters

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    //Getters

    public Long getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    //toString

    @Override
    public String toString() {
        return "StreamingService [serviceName=" + serviceName + "]";
    }

}
