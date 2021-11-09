package ru.bell.dao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;

public interface IncomeInvoiceDao {

    Mono<Boolean> delete(Long id);

    Mono<IncomeInvoice> get(Long id);

    Flux<IncomeInvoice> get();

    Mono<IncomeInvoice> create(IncomeInvoice invoice);

    Mono<IncomeInvoice> update(IncomeInvoice invoice);
}
