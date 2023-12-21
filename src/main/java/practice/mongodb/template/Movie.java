package practice.mongodb.template;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Movie {
    private String id;
    private String title;
    private List<Director> directors;
    private List<Genre> genres;

    public Movie(String title, List<Director> directors, List<Genre> genres) {
        this.title = title;
        this.directors = directors;
        this.genres = genres;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Director {
        String name;
    }


    @Getter
    public enum Genre {
        A, B, C;
    }
}
