package it.unibz.inf.ontop.sql.pool.impl;

import com.google.inject.Inject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unibz.inf.ontop.injection.OntopSystemSQLSettings;
import it.unibz.inf.ontop.sql.pool.JDBCConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * NOT a singleton
 */
public class HikariConnectionPool implements JDBCConnectionPool {


    private final HikariDataSource ds;

    @Inject
    private HikariConnectionPool(OntopSystemSQLSettings settings) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(settings.getJdbcUrl());
        config.setUsername(settings.getJdbcUser());
        config.setPassword(settings.getJdbcPassword());
        settings.getJdbcDriver()
                .ifPresent(config::setDriverClassName);

        config.setMinimumIdle(settings.getConnectionPoolInitialSize());
        config.setMaximumPoolSize(settings.getConnectionPoolMaxSize());
        config.setConnectionTimeout(settings.getConnectionTimeout());
        config.setReadOnly(true);

        ds = new HikariDataSource(config);
    }


    @Override
    public void close() {
        ds.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
