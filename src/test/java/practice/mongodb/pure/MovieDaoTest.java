package practice.mongodb.pure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MovieDaoTest {
    MovieDao movieDao = new MovieDao();

    @BeforeEach
    void beforeEach() {
        movieDao.deleteAll();
    }

    @Test
    void createMovieAndFindAllTest() {
        Movie movie = new Movie("movieA", List.of(new Movie.Director("kim")), List.of(Movie.Genre.A));
        movieDao.insert(movie);

        List<Movie> all = movieDao.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getTitle()).isEqualTo("movieA");
    }

    @Test
    void findByTitleTest() {
        String title = "movieA";
        Movie movie = new Movie(title, List.of(new Movie.Director("kim")), List.of(Movie.Genre.A));
        movieDao.insert(movie);

        Movie findMovie = movieDao.findByTitle(title).orElseThrow();
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
        assertThat(findMovie.getTitle()).isEqualTo(title);
    }

    @Test
    void findByDirectorName() {
        Movie.Director directorA = new Movie.Director("directorA");
        Movie.Director directorB = new Movie.Director("directorB");
        List<Movie.Director> directors = List.of(directorA, directorB);
        Movie movie = new Movie("movieA", directors, List.of(Movie.Genre.A));
        movieDao.insert(movie);

        List<Movie> moviesByDirectorA = movieDao.findByDirectorName(directorA.getName());
        List<Movie> moviesByDirectorB = movieDao.findByDirectorName(directorB.getName());

        assertThat(moviesByDirectorA).hasSize(1);
        assertThat(moviesByDirectorB).hasSize(1);
    }

    @Test
    void modifyTitleTest() {
        String title = "movieA";
        Movie movie = new Movie(title, List.of(new Movie.Director("kim")), List.of(Movie.Genre.A));
        movieDao.insert(movie);

        String newTitle = "movieB";
        movieDao.modifyTitle(movie, newTitle);

        Movie findMovie = movieDao.findByTitle(newTitle).get();
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
    }
}
