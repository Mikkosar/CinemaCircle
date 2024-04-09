package hh.sof3.cinemacircle.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class MovieList {

    //Attributes

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long listid;
        @NotBlank
        private String name;
        private String desc;

        @ManyToMany(cascade = { CascadeType.MERGE })
        @JoinTable(
            name = "Movies_MovieLists",
            joinColumns = { @JoinColumn(name = "listid") },
            inverseJoinColumns = { @JoinColumn(name = "movieid") }
        )
        List<Movie> movies = new ArrayList<>();

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "id") // FK
        private User user;

    //Constructor

        public MovieList(String name, String desc, User user) {
            this.name = name;
            this.desc = desc;
            this.user = user;
        }

    //Null Constructor

        public MovieList() {
            super();
        }

        public void hasMovie(Movie movie) {
            movies.add(movie);
        }
        

    //Setters

        public void setListid(Long listid) {
            this.listid = listid;
        }

        public void setMovieid(Long listid) {
            this.listid = listid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setMovies(List<Movie> movies) {
            this.movies = movies;
        }
        
        public void setUser(User user) {
            this.user = user;
        }

    //Getters


        public Long getListid() {
            return listid;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public List<Movie> getMovies() {
            return movies;
        }

        public User getUser() {
            return user;
        }

    //ToString


}