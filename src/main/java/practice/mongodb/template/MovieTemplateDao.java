package practice.mongodb.template;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

@Repository
@RequiredArgsConstructor
public class MovieTemplateDao {
    private final MongoTemplate template;

    public Movie insert(Movie movie) {
        return template.insert(movie);
    }

    public Optional<Movie> findById(String id) {
        Movie movie = template.findById(id, Movie.class);
        return Optional.ofNullable(movie);
    }

    public List<Movie> findAll() {
        return template.findAll(Movie.class);
    }

    public Optional<Movie> findByTitle(String title) {
        Movie movie = template.findOne(query(where("title").is(title)), Movie.class);
        return Optional.ofNullable(movie);
    }

    public List<Movie> findByDirectorName(String name) {
        return template.find(query(where("directors.name").is(name)), Movie.class);
    }

    public void modifyTitle(Movie movie, String title) {
        Query query = query(where("_id").is(movie.getId()));
        Update update = new Update().set("title", title);
        template.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Movie.class);
    }

    public void deleteByTitle(String title) {
        template.remove(query(where("title").is(title)), Movie.class);
    }

    public void deleteById(ObjectId objectId) {
        Query query = query(where("_id").is(objectId));
        template.remove(query, Movie.class);
    }

    public void deleteAll() {
        template.dropCollection(Movie.class);
    }
}
