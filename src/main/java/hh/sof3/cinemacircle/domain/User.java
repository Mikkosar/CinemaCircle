package hh.sof3.cinemacircle.domain;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "users")
public class User {

    //Attributes

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false)
        private Long id;

        
        @Column(name = "username", nullable = false, unique = true)
        @NotBlank
        private String username;

        @Column(name = "password", nullable = false)
        @NotBlank
        private String passwordHash;

        
        @Column(name = "email")
        @NotBlank
        private String email;

        @Column(name = "role", nullable = false)
        private String role;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
        @JsonIgnoreProperties ("user")
        @Column(name = "collections", nullable = true)
        private List<MovieList> collections;

    //Constructor

        public User(String username, String passwordHash, String email, String role) {
            super();
            this.username = username;
            this.passwordHash = passwordHash;
            this.email = email;
            this.role = role;
        }

    //Null Constructor

        public User() {
            super();
            this.passwordHash = null;
        }

    //Setters

        public void setId(Long id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPasswordHash(String passwordHash) {
            if (passwordHash != null && !passwordHash.isEmpty()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                this.passwordHash = passwordEncoder.encode(passwordHash);
            }
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setCollections(List<MovieList> collections) {
            this.collections = collections;
        }

    //Getters

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        public List<MovieList> getCollections() {
            return collections;
        }
}
