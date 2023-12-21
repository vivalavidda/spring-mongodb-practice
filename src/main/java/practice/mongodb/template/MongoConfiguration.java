//package practice.mongodb.template;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoClientFactoryBean;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//
//@Configuration
//public class MongoConfiguration {
//
//    public static final String CONNECTION_STRING = "mongodb://127.0.0.1:27017,127.0.0.1:27018,127.0.0.1:27019/?replicaSet=rs0";
//
//    private final ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
//
////    @Bean
////    //  standard MongoClient instance using MongoClientFactoryBean
////    public MongoClient mongoClient() {
////        return MongoClients.create(connectionString);
////    }
//
////    @Bean
////    public MongoDatabaseFactory mongoDatabaseFactory() {
////        return new SimpleMongoClientDatabaseFactory(MongoClients.create(), "database");
////    }
//
//    /*
//     * Factory bean that creates the com.mongodb.client.MongoClient instance
//     * MongoClient와 더불어 예외를 스프링 예외로 변환해줌.
//     */
//    @Bean
//    public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setConnectionString(connectionString);
//        return mongo;
//    }
//}
