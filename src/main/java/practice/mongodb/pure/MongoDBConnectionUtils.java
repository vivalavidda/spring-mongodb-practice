package practice.mongodb.pure;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.TimeUnit;

public class MongoDBConnectionUtils {

//    public static final String CONNECTION_STRING = "mongodb://127.0.0.1:27017/?replicaSet=rs0";
    public static final String CONNECTION_STRING = "mongodb://127.0.0.1:27017,127.0.0.1:27018,127.0.0.1:27019/?replicaSet=rs0";
    private static final String DB_NAME = "TEST";
    private static final TransactionOptions options;


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

        options = TransactionOptions.builder()
                // 데이터를 읽을 때의 일관성 수준
                // 현재 노드의 가장 최신 데이터를 읽지만, 이 데이터가 다른 노드에 복제되었는지는 고려하지 않음.
                // 즉 빠른 읽기를 위해 일관성을 일부 포기
                .readConcern(ReadConcern.LOCAL)
                // 데이터를 쓸 때의 신뢰성 수준 -> 대다수의 노드에 복제되기 전까지는 쓰기 작업이 완료되지 않았다고 간주
                // 데이터의 일관성을 보장하지만 응답 시간이 느려질 수 있음.
                .writeConcern(WriteConcern.MAJORITY)
                .readPreference(ReadPreference.primary())   // 데이터를 읽을 때 어떤 노드를 사용할지 -> 프라이머리 노드에서 읽기
                .build();
        /**
         커넥션 풀에서 커넥션을 받는 시점은 다음과 같다.

         쿼리 실행 시: 클라이언트 코드가 데이터베이스 쿼리를 실행할 때, 예를 들어 find(), insertOne(), updateOne() 등의 메서드를 호출할 때, 드라이버는 커넥션 풀에서 사용 가능한 커넥션을 자동으로 할당받습니다.

         트랜잭션 시작 시: 클라이언트 코드가 트랜잭션을 시작할 때, 예를 들어 ClientSession.startTransaction()을 호출할 때, 드라이버는 커넥션 풀에서 커넥션을 할당받아 해당 트랜잭션에 사용합니다.
        */
    }

    public static MongoDatabase getDbConnection() {
        return mongoClient.getDatabase(DB_NAME);
    }

    public static <T> T doInTransactionWithReturn(TransactionalOperationWithReturn<T> operation) {
        try (ClientSession session = mongoClient.startSession()) {
            session.startTransaction(options);
            try {
                T result = operation.execute(session);
                session.commitTransaction();
                return result;
            } catch (Exception e) {
                session.abortTransaction();
                throw e;
            }
        }
    }

    public static <T> void doInTransaction(TransactionalOperation<T> operation) {
        try (ClientSession session = mongoClient.startSession()) {
            session.startTransaction(options);
            try {
                operation.execute(session);
                session.commitTransaction();
            } catch (Exception e) {
                session.abortTransaction();
                throw e;
            }
        }
    }

    @FunctionalInterface
    public interface TransactionalOperationWithReturn<T> {
        T execute(ClientSession session);
    }

    @FunctionalInterface
    public interface TransactionalOperation<T> {
        void execute(ClientSession session);
    }

    public static void close() {
        mongoClient.close();
    }
}
