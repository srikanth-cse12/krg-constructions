package com.krg.construction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${spring.datasource.url:}")
    private String datasourceUrl;

    @Value("${DATABASE_URL:}")
    private String databaseUrlEnv;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.maximum-pool-size:10}")
    private int maxPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle:2}")
    private int minIdle;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // Check explicit datasource URL first, then generic cloud DATABASE_URL
        String resolvedUrl = (datasourceUrl != null && !datasourceUrl.isBlank())
                ? datasourceUrl
                : databaseUrlEnv;

        if (resolvedUrl != null && !resolvedUrl.isBlank()) {
            resolvedUrl = resolvedUrl.trim();

            if (resolvedUrl.startsWith("postgres://") || resolvedUrl.startsWith("postgresql://")) {
                // Parse Cloud / Neon URI format: postgresql://[user]:[password]@[host]:[port]/[database]?[query]
                try {
                    URI uri = new URI(resolvedUrl);
                    String host = uri.getHost();
                    int port = (uri.getPort() > 0) ? uri.getPort() : 5432;
                    String path = uri.getPath(); // /dbname
                    String query = uri.getQuery();

                    StringBuilder jdbcBuilder = new StringBuilder();
                    jdbcBuilder.append("jdbc:postgresql://").append(host).append(":").append(port).append(path);
                    if (query != null && !query.isBlank()) {
                        jdbcBuilder.append("?").append(query);
                    } else {
                        // Neon requires SSL
                        jdbcBuilder.append("?sslmode=require");
                    }

                    String jdbcUrl = jdbcBuilder.toString();
                    config.setJdbcUrl(jdbcUrl);
                    config.setDriverClassName("org.postgresql.Driver");

                    String userInfo = uri.getUserInfo();
                    if (userInfo != null && userInfo.contains(":")) {
                        String[] credentials = userInfo.split(":", 2);
                        config.setUsername(credentials[0]);
                        config.setPassword(credentials[1]);
                    } else {
                        if (username != null && !username.isBlank()) config.setUsername(username);
                        if (password != null && !password.isBlank()) config.setPassword(password);
                    }
                    log.info("Configured Neon PostgreSQL datasource for host: {}", host);
                } catch (Exception e) {
                    log.warn("Could not parse URI format. Attempting direct JDBC usage: {}", e.getMessage());
                    config.setJdbcUrl(resolvedUrl);
                    if (username != null && !username.isBlank()) config.setUsername(username);
                    if (password != null && !password.isBlank()) config.setPassword(password);
                }
            } else {
                // Direct JDBC format
                config.setJdbcUrl(resolvedUrl);
                if (username != null && !username.isBlank()) config.setUsername(username);
                if (password != null && !password.isBlank()) config.setPassword(password);
                if (driverClassName != null && !driverClassName.isBlank()) {
                    config.setDriverClassName(driverClassName);
                }
                String sanitized = resolvedUrl.replaceAll(":[^:@]+@", ":****@");
                log.info("Configured JDBC datasource: {}", sanitized);
            }
        } else {
            // Local development default fallback (PostgreSQL-compatible H2 in-memory mode)
            log.info("No cloud or external DATABASE_URL provided. Initializing local in-memory PostgreSQL-compatible database for development.");
            config.setJdbcUrl("jdbc:h2:mem:krgdevdb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH;DB_CLOSE_DELAY=-1");
            config.setDriverClassName("org.h2.Driver");
            config.setUsername("sa");
            config.setPassword("");
        }

        // HikariCP Connection Pool optimization
        config.setPoolName("KrgHikariPool");
        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdle);
        config.setConnectionTimeout(20000);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(900000);
        config.setKeepaliveTime(60000);

        return new HikariDataSource(config);
    }
}
