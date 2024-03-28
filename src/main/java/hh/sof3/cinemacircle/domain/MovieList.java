package hh.sof3.cinemacircle.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class MovieList {

    //Attributes

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long listid;
        private String name;
        private String desc;

        @ManyToMany(cascade = { CascadeType.MERGE })
        @JoinTable(
            name = "Movies_MovieLists",
            joinColumns = { @JoinColumn(name = "listid") },
            inverseJoinColumns = { @JoinColumn(name = "movieid") }
        )
        List<Movie> movies = new ArrayList<>();

    //Constructor

        public MovieList(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

    //Null Constructor

        public MovieList() {

        }

        public void hasMovie(Movie movie) {
            movies.add(movie);
        }

    //Setters

        public void setMovieid(Long listid) {
            this.listid = listid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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


    //ToString


}