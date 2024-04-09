package hh.sof3.cinemacircle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof3.cinemacircle.web.CollectionsController;
import hh.sof3.cinemacircle.web.HomePageController;
import hh.sof3.cinemacircle.web.MovieControllers;

@SpringBootTest
public class CinemaCircleSmokeTests {

    @Autowired
    private MovieControllers movieController;

        @Test
        public void movieContextLoads() throws Exception {
            assertThat(movieController).isNotNull();
        }
    
    @Autowired
    private CollectionsController collectionsController;

        @Test
        public void collectionContextLoads() throws Exception {
            assertThat(collectionsController).isNotNull();
        }

    @Autowired
    private HomePageController homePageController;

        @Test
        public void homePageContextLoads() throws Exception {
            assertThat(homePageController).isNotNull();
        }
}
