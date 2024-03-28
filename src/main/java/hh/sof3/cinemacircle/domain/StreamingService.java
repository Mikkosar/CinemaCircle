package hh.sof3.cinemacircle.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Streaming_Service")
public class StreamingService {

    //Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String serviceName;

    @ManyToMany(mappedBy = "streamingServices")
    private List<Movie> movies = new ArrayList<>();

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

    public void hasMovie(Movie movie) {
        movies.add(movie);
    }

    //Setters

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    //Getters

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    //toString

    @Override
    public String toString() {
        return "StreamingService [serviceName=" + serviceName + "]";
    }

}
