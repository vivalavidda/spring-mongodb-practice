package practice.mongodb.pure;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.TimeUnit;

public class MongoDBConnectionUtils {

    public static final String CONNECTION_STRING = "mongodb://root:1234@localhost:27017/?retryWrites=false";
    private static final String DB_NAME = "TEST";


    private final static MongoClient mongoClient;

    static {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .applyToConnectionPoolSettings(builder ->
                        builder.maxSize(10)
                                .minSize(1)
                                .maxWaitTime(1000, TimeUnit.MILLISECONDS))
                .build();

        mongoClient = MongoClients.create(settings);

        /**
         커넥션 풀에서 커넥션을 받는 시점은 다음과 같다.

         쿼리 실행 시: 클라이언트 코드가 데이터베이스 쿼리를 실행할 때, 예를 들어 find(), insertOne(), updateOne() 등의 메서드를 호출할 때, 드라이버는 커넥션 풀에서 사용 가능한 커넥션을 자동으로 할당받습니다.

         트랜잭션 시작 시: 클라이언트 코드가 트랜잭션을 시작할 때, 예를 들어 ClientSession.startTransaction()을 호출할 때, 드라이버는 커넥션 풀에서 커넥션을 할당받아 해당 트랜잭션에 사용합니다.
        */
    }

    public static MongoDatabase getDbConnection() {
        return mongoClient.getDatabase(DB_NAME);
    }

    public static void close() {
        mongoClient.close();
    }
}
