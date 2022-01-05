package ua.com.alevel.config.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.config.jpa.JpaPropertyConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MySqlJpaConfig implements JpaConfig {

    private final JpaPropertyConfig jpaPropertyConfig;

    private Connection connection;
    private Statement statement;

    @Autowired
    public MySqlJpaConfig(JpaPropertyConfig jpaPropertyConfig) {
        this.jpaPropertyConfig = jpaPropertyConfig;
        connect();
    }

    @Override
    public boolean connect() {
        try {
            Class.forName(jpaPropertyConfig.getDriverClassName());
            connection = DriverManager.getConnection(
                    jpaPropertyConfig.getUrl(),
                    jpaPropertyConfig.getUsername(),
                    jpaPropertyConfig.getPassword()
            );
            statement = connection.createStatement();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public String toString() {
        return "MySqlJpaConfig{" +
                "jpaPropertyConfig=" + jpaPropertyConfig +
                ", connection=" + connection +
                ", statement=" + statement +
                '}';
    }
}