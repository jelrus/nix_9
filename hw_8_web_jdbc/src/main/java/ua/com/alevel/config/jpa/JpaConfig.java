package ua.com.alevel.config.jpa;

import java.sql.Connection;
import java.sql.Statement;

public interface JpaConfig {

    boolean connect();

    Connection getConnection();

    Statement getStatement();
}