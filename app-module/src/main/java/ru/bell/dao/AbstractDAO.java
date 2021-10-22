package ru.bell.dao;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

public abstract class AbstractDAO {
    private final ConnectionFactory connectionFactory;

    protected AbstractDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    Mono<Connection> connection() {
        return Mono.from(this.connectionFactory.create());
    }
}
