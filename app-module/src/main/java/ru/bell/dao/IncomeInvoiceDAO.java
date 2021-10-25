package ru.bell.dao;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;

import java.time.LocalDateTime;

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
                .flatMap(r -> r.map((row, rowMetadata) ->
                        new IncomeInvoice(
                                row.get("id", Long.class),
                                row.get("date", LocalDateTime.class),
                                row.get("no", Integer.class),
                                row.get("from_ca", String.class),
                                row.get("to_ca", String.class),
                                row.get("doc_sum", Double.class),
                                row.get("description", String.class)))).next();
    }

    public Flux<IncomeInvoice> get() {
        return this.connection()
                .flatMapMany(connection ->
                        Flux.from(connection.createStatement("SELECT * FROM income_invoice").execute())
                                .flatMap(r -> r.map(((row, rowMetadata)
                                        -> new IncomeInvoice(
                                        row.get("id", Long.class),
                                        row.get("date", LocalDateTime.class),
                                        row.get("no", Integer.class),
                                        row.get("from_ca", String.class),
                                        row.get("to_ca", String.class),
                                        row.get("doc_sum", Double.class),
                                        row.get("description", String.class))))));
    }

    public Mono<IncomeInvoice> create(IncomeInvoice invoice) {
        Mono<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("INSERT INTO income_invoice (date, no, from_ca, to_ca, doc_sum, description) " +
                                "VALUES ($1, $2, $3, $4, $5, $6)")
                        .bind("$1", invoice.getDate())
                        .bind("$2", invoice.getNo())
                        .bind("$3", invoice.getFrom())
                        .bind("$4", invoice.getTo())
                        .bind("$5", invoice.getSum())
                        .bind("$6", invoice.getDescription())
                        .add()
                        .execute()).next();
        return flatMapMany
                .flatMapMany(x -> Mono.just(new IncomeInvoice(
                        invoice.getId(),
                        invoice.getDate(),
                        invoice.getNo(),
                        invoice.getFrom(),
                        invoice.getTo(),
                        invoice.getSum(),
                        invoice.getDescription()))).next();
    }

    public Flux<IncomeInvoice> update(IncomeInvoice invoice) {
        Flux<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("UPDATE income_invoice SET  date = $2, no = $3, from_ca = $4, to_ca = $5, doc_sum = $6, description = $7 WHERE id = $1")
                        .bind("$1", invoice.getId())
                        .bind("$2", invoice.getDate())
                        .bind("$3", invoice.getNo())
                        .bind("$4", invoice.getFrom())
                        .bind("$5", invoice.getTo())
                        .bind("$6", invoice.getSum())
                        .bind("$7", invoice.getDescription())
                        .add()
                        .execute());
        return flatMapMany
                .switchMap(x -> Flux.just(new IncomeInvoice(
                        invoice.getId(),
                        invoice.getDate(),
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
