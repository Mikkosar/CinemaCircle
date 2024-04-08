package hh.sof3.cinemacircle.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private Long serviceid;
    private String servicename;

    @JsonIgnore
    @ManyToMany(mappedBy = "streamingServices")
    private List<Movie> movies = new ArrayList<>();

    //Constructor

    public StreamingService(String serviceName) {
        super();
        this.servicename = serviceName;
    }

    //Null Constructor

    public StreamingService() {
        super();
        this.serviceid = null;
        this.servicename = null;
    }

    public void hasMovie(Movie movie) {
        movies.add(movie);
    }

    //Setters

    public void setServiceId(Long serviceId) {
        this.serviceid = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.servicename = serviceName;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    //Getters

    public Long getServiceId() {
        return serviceid;
    }

    public String getServiceName() {
        return servicename;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    //toString

    @Override
    public String toString() {
        return "StreamingService [serviceName=" + servicename + "]";
    }

}
