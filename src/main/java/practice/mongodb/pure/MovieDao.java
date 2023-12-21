package practice.mongodb.pure;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Repository
public class MovieDao {
    private final MongoCollection<Movie> collections;

    {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnectionUtils.getDbConnection().withCodecRegistry(pojoCodecRegistry);
        collections = database.getCollection("movies", Movie.class);
    }


    public Movie insert(Movie movie) {
        InsertOneResult insertOneResult = collections.insertOne(movie);
        movie.setId(insertOneResult.getInsertedId().asObjectId().getValue());
        return movie;
    }

    public Optional<Movie> findById(ObjectId objectId) {
        ArrayList<Movie> movie = collections.find(eq("_id", objectId)).into(new ArrayList<>());
        return movie.stream().findAny();
    }

    public List<Movie> findAll() {
        FindIterable<Movie> movieFindIterable = collections.find().batchSize(100);
        return movieFindIterable.into(new ArrayList<>());
    }

    public Optional<Movie> findByTitle(String title) {
        List<Movie> foundMovies = findMoviesByQuery(new Document("title", title));
        if (foundMovies.size() > 1) {
            throw new RuntimeException("검색된 영화가 2개 이상입니다");
        }
        return foundMovies.stream().findFirst();
    }


    public List<Movie> findByDirectorName(String name) {
        return findMoviesByQuery(new Document("directors.name", name));
    }

    public void modifyTitle(Movie movie, String title) {
        collections.findOneAndUpdate(eq("_id", movie.getId()), set("title", title));
    }

    public void deleteByTitle(String title) {
        collections.findOneAndDelete(eq("title", title));
    }

    public void deleteById(ObjectId objectId) {
        collections.deleteOne(eq("_id", objectId));
    }

    public void deleteAll() {
        collections.deleteMany(new Document()); // 모든 문서 삭제
    }

    private List<Movie> findMoviesByQuery(Document query) {
        return collections.find(query)
                .into(new ArrayList<>());
    }
}
