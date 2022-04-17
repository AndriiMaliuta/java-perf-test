package com.anma.sql;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBTest {
    public static void testSql() {
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
