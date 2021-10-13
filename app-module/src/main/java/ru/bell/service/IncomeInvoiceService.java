package ru.bell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bell.dao.IncomeInvoiceDAO;
import ru.bell.dto.IncomeInvoice;

import java.util.List;

@Service
public class IncomeInvoiceService {
    private final IncomeInvoiceDAO repository;

    @Autowired
    public IncomeInvoiceService(IncomeInvoiceDAO repository) {
        this.repository = repository;
    }

    public List<IncomeInvoice> getIncomeInvoices() {
        return  repository.get();
    }

    public IncomeInvoice getIncomeInvoiceById(Long id) {
        return repository.get(id);
    }

    public boolean deleteIncomeInvoiceById(Long id) {
        return repository.delete(id);
    }

    public IncomeInvoice createIncomeInvoice(IncomeInvoice incomeInvoice) {
        return repository.create(incomeInvoice);
    }

    public IncomeInvoice updateIncomeInvoice(IncomeInvoice incomeInvoice) {
        return repository.update(incomeInvoice);
    }
}
