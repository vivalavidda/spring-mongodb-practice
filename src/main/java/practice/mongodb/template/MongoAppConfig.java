//package practice.mongodb.template;
//
//import com.mongodb.ServerAddress;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@PropertySource("classpath:/com/myapp/mongodb/config/mongo.properties")
//public class MongoAppConfig extends AbstractMongoClientConfiguration {
//
//    @Autowired
//    Environment env;
//
//    @Override
//    public String getDatabaseName() {
//        return "database";
//    }
//
//    @Override
//    protected void configureClientSettings(Builder builder) {
//
//        builder.applyToClusterSettings(settings -> {
//            settings.hosts(singletonList(
//                    new ServerAddress(env.getProperty("mongo.host"), env.getProperty("mongo.port", Integer.class))));
//        });
//
//        builder.applyToConnectionPoolSettings(settings -> {
//
//            settings.maxConnectionLifeTime(env.getProperty("mongo.pool-max-life-time", Integer.class), TimeUnit.MILLISECONDS)
//                    .minSize(env.getProperty("mongo.pool-min-size", Integer.class))
//                    .maxSize(env.getProperty("mongo.pool-max-size", Integer.class))
//                    .maintenanceFrequency(10, TimeUnit.MILLISECONDS)
//                    .maintenanceInitialDelay(11, TimeUnit.MILLISECONDS)
//                    .maxConnectionIdleTime(30, TimeUnit.SECONDS)
//                    .maxWaitTime(15, TimeUnit.MILLISECONDS);
//        });
//    }
//}
