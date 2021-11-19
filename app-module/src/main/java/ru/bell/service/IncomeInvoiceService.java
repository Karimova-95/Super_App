package ru.bell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bell.dao.IncomeInvoiceDAO;
import ru.bell.dto.IncomeInvoice;

import java.util.List;

@Service
public class IncomeInvoiceService {
    private final IncomeInvoiceDAO dao;

    @Autowired
    public IncomeInvoiceService(IncomeInvoiceDAO dao) {
        this.dao = dao;
    }

    public List<IncomeInvoice> getIncomeInvoices() {
        return  dao.get();
    }

    public IncomeInvoice getIncomeInvoiceById(Long id) {
        return dao.get(id);
    }

    public boolean deleteIncomeInvoiceById(Long id) {
        return dao.delete(id);
    }

    public IncomeInvoice createIncomeInvoice(IncomeInvoice incomeInvoice) {
        return dao.create(incomeInvoice);
    }

    public IncomeInvoice updateIncomeInvoice(IncomeInvoice incomeInvoice) {
        return dao.update(incomeInvoice);
    }
}
