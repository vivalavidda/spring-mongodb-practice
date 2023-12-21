package practice.mongodb.template;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieTemplateDao {
    private final MongoTemplate template;

    public Movie insert(Movie movie) {
        return template.insert(movie);
    }

    public Optional<Movie> findById(ObjectId objectId) {

        return null;
    }

    public List<Movie> findAll() {
        return template.findAll(Movie.class);
    }

    public Optional<Movie> findByTitle(String title) {

        return null;
    }

    public List<Movie> findByDirectorName(String name) {
        return null;
    }

    public void modifyTitle(Movie movie, String title) {
    }

    public void deleteByTitle(String title) {
    }

    public void deleteById(ObjectId objectId) {
    }

    public void deleteAll() {
    }
}
