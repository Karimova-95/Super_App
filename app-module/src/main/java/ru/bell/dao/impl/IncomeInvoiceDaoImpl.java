package ru.bell.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dao.IncomeInvoiceDao;
import ru.bell.dto.IncomeInvoice;
import ru.bell.repository.IncomeInvoiceRepository;

@Service
@RequiredArgsConstructor
public class IncomeInvoiceDaoImpl implements IncomeInvoiceDao {

    private final IncomeInvoiceRepository repository;

    public Mono<Boolean> delete(Long id) {
        return repository.deleteIncomeInvoiceById(id);
    }

    public Mono<IncomeInvoice> get(Long id) {
        return repository.findById(id);
    }

    public Flux<IncomeInvoice> get() {
        return repository.findAll();
    }

    public Mono<IncomeInvoice> create(IncomeInvoice invoice) {
        return repository.save(invoice);
    }

    public Mono<IncomeInvoice> update(IncomeInvoice invoice) {
        return repository.save(invoice);
    }
}
