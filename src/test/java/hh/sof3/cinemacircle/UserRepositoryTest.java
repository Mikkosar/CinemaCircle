package hh.sof3.cinemacircle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3.cinemacircle.domain.User;
import hh.sof3.cinemacircle.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    //Here we will test Userrepos functionality (create, findByName, delete)

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {
        User user = new User(
            "test",
            "test",
            "test",
            "user"
        );
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void findByName() {
        User user = new User(
            "test",
            "test",
            "test",
            "user"
        );
        userRepository.save(user);

        List<User> users = new ArrayList<>();
        users.add(userRepository.findByUsername("test"));

        assertThat(users).hasSize(1);
        assertThat(users.get(0).getEmail()).isEqualTo("test");
    }
    
    @Test
    public void deleteUser() {
        User user = new User(
            "test",
            "test",
            "test",
            "user"
        );
        userRepository.save(user);
        userRepository.delete(user);

        assertThat(userRepository.findByUsername("test")).isNull();
    }
}
