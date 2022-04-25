package com.anma.react;

import com.github.javafaker.Faker;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.api.PostgresqlConnection;
import io.r2dbc.postgresql.api.PostgresqlResult;
import io.r2dbc.postgresql.api.PostgresqlStatement;
import io.r2dbc.spi.*;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.naming.ldap.ControlFactory;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class ReactiveTests {

    @Test
    void createPsql() {
        var start = Instant.now();
        Map<String, String> options = new HashMap<>();
        options.put("lock_timeout", "10s");

        PostgresqlConnectionFactory factory = new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(System.getenv("PSQL_HOST"))
                        .port(5432)
                        .username("jqnqjfrd").password(System.getenv("ELEPH_PASS"))
                        .database("jqnqjfrd")
                        .options(options).build());

        Faker faker = new Faker();
        Mono<PostgresqlConnection> connection = factory.create();
        var body = "'Geralt slayed " + faker.witcher().monster() + "'";

        for (int i = 70000; i < 70100; i++) {
            var query = String.format("INSERT INTO comments(id, body, category, created_at, author_id) " +
                            "VALUES(%d,%s,%s,%s,%d)", i,
                    body, "'misc'",
                    "'" + LocalDateTime.now() + "'", 1011);

//            connection.flatMapMany(con ->
////                Mono<Void> transaction = con.beginTransaction();
////                PostgresqlStatement statement = con.createStatement(query);
////                Publisher<Void> savepoint = connection.block().createSavepoint("savepoint");
////                Flux<PostgresqlResult> execute = statement.execute();
////                execute.subscribe();
//
//                    con.createStatement(query).execute().doOnNext(System.out::println).subscribe()).doOnNext(System.out::println).subscribe();

            /*
            .doFirst(() -> System.out.println("Initiating creation"))
                        .doFinally((el) -> System.out.println("Creation finished!"))
                        .doOnNext(el -> System.out.println("Comment created " + el))
                        .doOnError(err -> System.out.println(err))
                        .subscribe();           //LocalDateTime.now()
             */
        }
        System.out.println(">> Script took " + Duration.between(start, Instant.now()).toMillis());

    }

    @Test
    void createCommentsSpi() {

        ConnectionFactory factory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "pool")
                .option(PROTOCOL, "postgresql") // driver identifier, PROTOCOL is delegated as DRIVER by the pool.
                .option(HOST, System.getenv("PSQL_HOST"))
                .option(PORT, 5432)
                .option(USER, "jqnqjfrd")
                .option(PASSWORD, System.getenv("ELEPH_PASS"))
                .option(DATABASE, "jqnqjfrd")
                .build());

        Publisher<?extends Connection> pooledConnectionFactory = factory.create();
//        Mono<Connection> connectionMono = Mono.from(pooledConnectionFactory.create());

//        pooledConnectionFactory.subscribe(con -> con);

        System.out.println(factory.getMetadata().getName());

    }

    @Test
    void progCreateComments() {
        ConnectionFactory factory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "pool")
                .option(PROTOCOL, "postgresql") // driver identifier, PROTOCOL is delegated as DRIVER by the pool.
                .option(HOST, System.getenv("PSQL_HOST"))
                .option(PORT, 5432)
                .option(USER, "jqnqjfrd")
                .option(PASSWORD, System.getenv("ELEPH_PASS"))
                .option(DATABASE, "jqnqjfrd")
                .build());

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder(factory)
                .maxIdleTime(Duration.ofMillis(1000))
                .maxSize(20)
                .build();

        ConnectionPool pool = new ConnectionPool(configuration);

        Mono<Connection> connection = pool.create();
//        connection.map(con -> con.createStatement("select * from comments").execute());
        connection.doOnNext(c -> {
            Statement statement = c.createStatement("select * from comments");
            Publisher<? extends Result> execute = statement.execute();

        }).subscribe();

//        Connection connection = …;
//        Mono<Void> release = connection.close(); // released the connection back to the pool

// application shutdown
        pool.dispose();
    }

    @Test
    void r2Off() {
        ConnectionFactory connectionFactory = ConnectionFactories
                .get("r2dbc:postgresql://jqnqjfrd:" + System.getenv("ELEPH_PASS") + "@" + System.getenv("PSQL_HOST") + "/jqnqjfrd");

        Mono.from(connectionFactory.create())
                .flatMapMany(connection -> connection
                        .createStatement("SELECT * FROM comments WHERE id = $1")
                        .bind("$1", 10)
                        .execute())
                .flatMap(result -> result
                        .map((row, rowMetadata) -> row.get("body", String.class)))
                .doOnNext(System.out::println)
                .subscribe();
    }



}
