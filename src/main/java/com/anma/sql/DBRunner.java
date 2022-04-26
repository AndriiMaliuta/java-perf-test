package com.anma.sql;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.sql.Connection;
import java.sql.DriverManager;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class DBRunner {
    public static void main(String[] args) {
        reactiveConnect();
    }

    private static void reactiveConnect() {
        ConnectionFactory factory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "pool")
                .option(PROTOCOL, "postgresql") // driver identifier, PROTOCOL is delegated as DRIVER by the pool.
                .option(HOST, System.getenv("PSQL_HOST"))
                .option(PORT, 5432)
                .option(USER, "jqnqjfrd")
                .option(PASSWORD, System.getenv("ELEPH_PASS"))
                .option(DATABASE, "jqnqjfrd")
                .build());

        System.out.println(factory.toString());

        Publisher<? extends io.r2dbc.spi.Connection> pooledConnectionFactory = factory.create();
        Mono<io.r2dbc.spi.Connection> connectionMono = Mono.from(pooledConnectionFactory);
        connectionMono.map(c -> c).doOnNext(System.out::println).subscribe();

        System.out.println(factory.getMetadata().getName());
    }

    private static void simpleConnect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://castor.db.elephantsql.com/jqnqjfrd",
                    "jqnqjfrd",
                    System.getenv("ELEPH_PASS"));
            var statement = connection.prepareStatement("select * from cats");
            var res = statement.executeQuery();
            while (res.next()) {
                System.out.println(res.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
