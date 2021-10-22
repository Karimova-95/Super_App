package ru.bell.dao;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;

@Repository
public class IncomeInvoiceDAO {
    private final ConnectionFactory connectionFactory;

    public IncomeInvoiceDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Mono<Void> delete(Integer id) {
        return this.connection()
                .flatMapMany(c -> c.createStatement("DELETE FROM income_invoice WHERE id = $1")
                        .bind("$1", id)
                        .execute())
                .then();
    }

    public Mono<IncomeInvoice> get(Integer id) {
        return this.connection()
                .flatMapMany(c -> c.createStatement("SELECT * FROM income_invoice WHERE id = $1")
                        .bind("$1", id)
                        .execute())
                .flatMap(r -> r.map((((row, rowMetadata) ->
                        new IncomeInvoice
                                (row.get("id", Integer.class),
                                row.get("no", Integer.class),
                                row.get("from_ca", String.class),
                                row.get("to_ca", String.class),
                                row.get("doc_sum", Integer.class),
                                row.get("description", String.class)))))).next();
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
                                "VALUES ($1, $2, $3, $4, $5)")
                        .bind("$1", invoice.getNo())
                        .bind("$2", invoice.getFrom())
                        .bind("$3", invoice.getTo())
                        .bind("$4", invoice.getSum())
                        .bind("$5", invoice.getDescription())
                        .add()
                        .execute());
        return flatMapMany
                .switchMap(x -> Flux.just(new IncomeInvoice
                        (invoice.getId(),
                                invoice.getNo(),
                                invoice.getFrom(),
                                invoice.getTo(),
                                invoice.getSum(),
                                invoice.getDescription())));
    }

    public Flux<IncomeInvoice> update(IncomeInvoice invoice) {
        Flux<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("UPDATE income_invoice SET  no = $2, from_ca = $3, to_ca = $4, doc_sum = $5, description = $6 WHERE id = $1")
                        .bind("$1", invoice.getId())
                        .bind("$2", invoice.getNo())
                        .bind("$3", invoice.getFrom())
                        .bind("$4", invoice.getTo())
                        .bind("$5", invoice.getSum())
                        .bind("$6", invoice.getDescription())
                        .add()
                        .execute());
        return flatMapMany
                .switchMap(x -> Flux.just(new IncomeInvoice
                        (invoice.getId(),
                                invoice.getNo(),
                                invoice.getFrom(),
                                invoice.getTo(),
                                invoice.getSum(),
                                invoice.getDescription())));
    }

    private Mono<Connection> connection() {
        return Mono.from(this.connectionFactory.create());
    }
}
