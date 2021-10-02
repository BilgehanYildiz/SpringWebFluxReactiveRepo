package com.example.webflux;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

//https://stackoverflow.com/questions/63646864/spring-boot-h2-console-returns-404

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {

        ConnectionFactoryOptions options =
                ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PROTOCOL, "file")  // file, mem
                .option(HOST, "localhost")
                .option(USER, "sa")
                .option(PASSWORD, "123")
                .option(DATABASE, "C:\\h2\\h2").build();


        //ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:file://C:/h2/h2");
        ConnectionFactory connectionFactory=ConnectionFactories.get(options);
        return connectionFactory;
    }
    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
