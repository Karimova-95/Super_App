package ru.bell.dao;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;

import java.sql.Timestamp;

@Repository
public class WebfluxIncomeInvoiceDAO {
    private final ConnectionFactory connectionFactory;


    public WebfluxIncomeInvoiceDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Mono<Void> delete(Integer id) {
        return this.connection()
                .flatMapMany(c -> c.createStatement("DELETE FROM income_invoice WHERE id = $1")
                        .bind("$1", id)
                        .execute())
                .then();
    }

    public Flux<IncomeInvoice> get() {
        return this.connection()
                .flatMapMany(connection ->
                        Flux.from(connection.createStatement("SELECT * FROM income_invoice").execute())
                                .flatMap(r -> r.map(((row, rowMetadata)
                                        -> new IncomeInvoice
                                        (row.get("id", Integer.class),
                                                row.get("no", Integer.class),
                                                row.get("from_ca", String.class),
                                                row.get("to_ca", String.class),
                                                row.get("doc_sum", Integer.class),
                                                row.get("description", String.class))))));
    }

    public Flux<IncomeInvoice> create(IncomeInvoice invoice) {
        Flux<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("INSERT INTO income_invoice (no, from_ca, to_ca, doc_sum, description) " +
                                "VALUES ($2, $3, $4, $5, $6)")
                        .bind("$2", invoice.getNo())
                        .bind("$3", invoice.getFrom())
                        .bind("$4", invoice.getTo())
                        .bind("$5", invoice.getSum())
                        .bind("$6", invoice.getDescription())
                        .add()
                        .execute());
        return flatMapMany
                .switchMap(x -> Flux.just(new IncomeInvoice
                        (invoice.getNo(),
                                invoice.getFrom(),
                                invoice.getTo(),
                                invoice.getSum(),
                                invoice.getDescription())));
    }

    private Mono<Connection> connection() {
        return Mono.from(this.connectionFactory.create());
    }
}
