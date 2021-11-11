package ru.bell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;
import ru.bell.repository.IncomeInvoiceRepository;

@RequiredArgsConstructor
@Service
public class IncomeInvoiceService {

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
