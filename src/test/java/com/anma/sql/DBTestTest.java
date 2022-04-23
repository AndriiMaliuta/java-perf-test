package com.anma.sql;

import com.anma.models.Comment;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class DBTestTest {

    @Test
    void createCommentsTest() throws ClassNotFoundException, SQLException {
        var start = Instant.now();
        var faker = new Faker();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://castor.db.elephantsql.com/jqnqjfrd",
                "jqnqjfrd",
                System.getenv("ELEPH_PASS"));
        createComments(faker, connection);
        System.out.println(">> Script took " + Duration.between(start, Instant.now()).toMillis());
    }



    @Test
    void testSql() {
        try {
            var now = Instant.now();
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://castor.db.elephantsql.com/jqnqjfrd",
                    "jqnqjfrd",
                    System.getenv("ELEPH_PASS"));
            var statement = connection.prepareStatement("select * from comments");
            var res = statement.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (res.next()) {
                Comment comment = new Comment();
                var id = res.getLong("id");
                comment.setId(id);
                var authorId = res.getLong("author_id");
                comment.setAuthorId(authorId);
                var body = res.getString("body");
                comment.setBody(body);
                var category = res.getString("category");
                comment.setCategory(category);
                var createdAt = res.getTimestamp("created_at");
                comment.setCreatedAt(createdAt.toLocalDateTime());
                comments.add(comment);
                System.out.println(comment.toString());
            }
            System.out.println(comments.size());
            System.out.println(">>> TIME = " + Duration.between(now, Instant.now()).toMillis() + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createComments(Faker faker, Connection connection) throws SQLException {
        for (int i = 69000; i < 70000; i++) {
            var body = "'Geralt slayed " + faker.witcher().monster() + "'";
            var query = String.format("INSERT INTO comments(id, body, category, created_at, author_id) " +
                            "VALUES(%d,%s,%s,%s,%d)", i,
                    body, "'misc'",
                    "'" + LocalDateTime.now() + "'", 1011);
            System.out.println(query);
            var statement = connection.prepareStatement(query);
            var res = statement.execute();
            System.out.println(res);
        }
    }
}