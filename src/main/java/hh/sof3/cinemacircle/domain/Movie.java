package hh.sof3.cinemacircle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "movie")
public class Movie {

    //Attributes

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long movieId;
        private String name;
        private String genre;
        private String desc;
        private String director;
        private String length;

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
            super();
            this.movieId = null;
            this.name = null;
            this.genre = null;
            this.desc = null;
            this.director = null;
            this.length = null;
        }

    //Setters

        public void setMovieId(Long movieId) {
            this.movieId = movieId;
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

    //Getters

        public Long getMovieId() {
            return movieId;
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

        //ToString

        @Override
        public String toString() {
            return "Movie [name=" + name + ", genre=" + genre + ", desc=" + desc + ", director=" + director
                    + ", length=" + length + "]";
        }

        
}
