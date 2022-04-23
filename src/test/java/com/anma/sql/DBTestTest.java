package com.anma.sql;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBTestTest {

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
            List<String> comments = new ArrayList<>();
            while (res.next()) {
                var str = res.getString("body");
                comments.add(str);

            }
            System.out.println(comments.size());
            System.out.println(">>> TIME = " + Duration.between(now, Instant.now()).toSeconds());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}