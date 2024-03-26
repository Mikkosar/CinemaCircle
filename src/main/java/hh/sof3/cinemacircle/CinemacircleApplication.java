package hh.sof3.cinemacircle;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.cinemacircle.domain.Movie;
import hh.sof3.cinemacircle.domain.MovieRepository;
import hh.sof3.cinemacircle.domain.StreamingService;
import hh.sof3.cinemacircle.domain.StreamingServiceRepository;

@SpringBootApplication
public class CinemacircleApplication {

	private static final Logger log = LoggerFactory.getLogger(CinemacircleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CinemacircleApplication.class, args);
	}

	@Bean
	public CommandLineRunner Cinemacircle(MovieRepository movieRepository, StreamingServiceRepository serviceRepository) {
		return (args) -> {

			log.info("Lets save couple of streaming services");

			StreamingService netflix = new StreamingService("Netflix");
			serviceRepository.save(netflix);
			StreamingService hbo = new StreamingService("Hbo");
			serviceRepository.save(hbo);
			StreamingService disneyPlus = new StreamingService("Disney Plus");
			serviceRepository.save(disneyPlus);

			log.info("Lets save couple of movies");

			List<Movie> movies = new ArrayList<>();

			movies.add(
				new Movie(
					"Shining",
					"Horror",
					"A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.", 
					"Stanley Kubrick", 
					"146min"
				)
			);

			movies.add(
				new Movie(
					"Raging Bull",
					"Biography",
					"The life of boxer Jake LaMotta, whose violence and temper that led him to the top in the ring destroyed his life outside of it.",
					"Martin Scorcese",
					"129min"
				)
			);

			movies.add(
				new Movie(
					"Stalker",
					"Sci-Fi",
					"A guide leads two men through an area known as the Zone to find a room that grants wishes.",
					"Andrei Tarkovsky",
					"162min"
				)
			);

			movies.add(
				new Movie(
					"Come And See",
					"War",
					"After finding an old rifle, a young boy joins the Soviet resistance movement against ruthless German forces and experiences the horrors of World War II.",
					"Elem Klimov",
					"142min"
				)
			);

			movies.add(
				new Movie(
					"Awakenings",
					"Drama",
					"The victims of an encephalitis epidemic many years ago have been catatonic ever since, but now a new drug offers the prospect of reviving them.",
					"Penny Marshall",
					"121min"
				)
			);

			movieRepository.saveAll(movies);
		};
	}
}
