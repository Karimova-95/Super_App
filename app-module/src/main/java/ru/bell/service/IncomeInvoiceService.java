package ru.bell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.bell.dao.WebfluxIncomeInvoiceDAO;
import ru.bell.dto.IncomeInvoice;

import java.util.List;

@Service
public class IncomeInvoiceService {

    private final WebfluxIncomeInvoiceDAO dao;

    @Autowired
    public IncomeInvoiceService(WebfluxIncomeInvoiceDAO dao) {
        this.dao = dao;
    }

    public List<IncomeInvoice> getIncomeInvoices() {
        return  dao.get().collectSortedList().block();
    }

//    public IncomeInvoice getIncomeInvoiceById(Long id) {
//        return dao.get(id);
//    }

    public boolean deleteIncomeInvoiceById(Integer id) {
        return dao.delete(id).subscribe().isDisposed();
    }

//    public IncomeInvoice create(IncomeInvoice incomeInvoice) {
//        Flux<IncomeInvoice> flux = dao.create(incomeInvoice).cast(IncomeInvoice.class);
//        return flux.collectSortedList().block();
//    }

//    public IncomeInvoice updateIncomeInvoice(IncomeInvoice incomeInvoice) {
//        return dao.update(incomeInvoice);
//    }
}
