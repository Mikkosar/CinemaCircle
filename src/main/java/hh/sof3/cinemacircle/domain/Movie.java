package hh.sof3.cinemacircle.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Movie")
public class Movie {

    //Attributes

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long movieid;

        @NotBlank
        private String name;

        private String genre;

        private String desc;

        @NotBlank
        private String director;

        private String length;

        @JsonIgnore
        @ManyToMany(cascade = { CascadeType.MERGE })
        @JoinTable(
            name = "Movie_StreamingService",
            joinColumns = { @JoinColumn(name = "movieId") },
            inverseJoinColumns =  { @JoinColumn(name = "serviceId") }
        )
        List<StreamingService> streamingServices = new ArrayList<>();

        @JsonIgnore
        @ManyToMany(mappedBy = "movies")
        List<MovieList> movieLists = new ArrayList<>();

    //Constructor

        public Movie(String name, String genre, String desc, String director, String length) {
            super();
            this.name = name;
            this.genre = genre;
            this.desc = desc;
            this.director = director;
            this.length = length;
        }

    //Null Constructor

        public Movie() {

        }

        public void inServices(StreamingService service) {
            streamingServices.add(service);
        }

    //Setters

        public void setMovieId(Long movieId) {
            this.movieid = movieId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public void setStreamingService(List<StreamingService> streamingService) {
            this.streamingServices = streamingService;
        }

    //Getters

        public Long getMovieId() {
            return movieid;
        }

        public String getName() {
            return name;
        }

        public String getGenre() {
            return genre;
        }

        public String getDesc() {
            return desc;
        }

        public String getDirector() {
            return director;
        }

        public String getLength() {
            return length;
        }

        public List<StreamingService> getStreamingService() {
            return streamingServices;
        }

        public List<MovieList> getMovieLists() {
            return movieLists;
        }

    //ToString


        @Override
        public String toString() {
            return "Movie [name=" + name + ", genre=" + genre + ", desc=" + desc + ", director=" + director
                    + ", length=" + length + "]";
        }
}
