package ru.bell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dao.IncomeInvoiceDao;
import ru.bell.dto.IncomeInvoice;

@RequiredArgsConstructor
@Service
public class IncomeInvoiceService {

    private final IncomeInvoiceDao dao;

    public Flux<IncomeInvoice> getIncomeInvoices() {
        return  dao.get();
    }

    public Mono<IncomeInvoice> getIncomeInvoiceById(Long id) {
        return dao.get(id);
    }

    public Mono<Boolean> deleteIncomeInvoiceById(Long id) {
        return dao.delete(id);
    }

    public Mono<IncomeInvoice> create(IncomeInvoice incomeInvoice) {
        return dao.create(incomeInvoice).cast(IncomeInvoice.class);
    }

    public Mono<IncomeInvoice> updateIncomeInvoice(IncomeInvoice incomeInvoice) {
        return dao.update(incomeInvoice);
    }
}
