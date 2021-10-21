package ru.bell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dao.IncomeInvoiceDAO;
import ru.bell.dto.IncomeInvoice;

@Service
public class IncomeInvoiceService {

    private final IncomeInvoiceDAO dao;

    @Autowired
    public IncomeInvoiceService(IncomeInvoiceDAO dao) {
        this.dao = dao;
    }

    public Flux<IncomeInvoice> getIncomeInvoices() {
        return  dao.get();
    }

    public Flux<IncomeInvoice> getIncomeInvoiceById(Integer id) {
        return dao.get(id);
    }

    public Mono<Void> deleteIncomeInvoiceById(Integer id) {
        return dao.delete(id);
    }

    public Flux<IncomeInvoice> create(IncomeInvoice incomeInvoice) {
        return dao.create(incomeInvoice).cast(IncomeInvoice.class);
    }

    public Flux<IncomeInvoice> updateIncomeInvoice(IncomeInvoice incomeInvoice) {
        return dao.update(incomeInvoice);
    }
}
