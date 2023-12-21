package practice.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepositoryDao extends MongoRepository<Movie, String> {

    Optional<Movie> findByTitle(String titleName);

    @Query("{'directors.name': ?0}")
    List<Movie> findByDirectorName(String name);

    @Query(value = "{'directors.name': ?0}", delete = true)
    void deleteByDirectorName(String directorName);

    void deleteAll();

    default void modifyTitle(Movie movie, String newTitle) {
        findById(movie.getId());
        movie.setTitle(newTitle);
        save(movie);
    }
}
