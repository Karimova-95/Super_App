package ru.bell.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bell.dto.IncomeInvoice;
import ru.bell.exception.NoSuchIncomeInvoiceException;
import ru.bell.service.IncomeInvoiceService;

import java.util.List;

@RestController
public class IncomeInvoiceController {
    private final IncomeInvoiceService service;

    public IncomeInvoiceController(IncomeInvoiceService service) {
        this.service = service;
    }

    @GetMapping("/income")
    @PreAuthorize("hasAuthority('READ')")
    public List<IncomeInvoice> getIncomeInvoices() {
        return service.getIncomeInvoices();
    }

    @GetMapping("/income/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public IncomeInvoice getIncomeInvoicesById(@PathVariable Long id) {
        IncomeInvoice invoice = service.getIncomeInvoiceById(id);
        if (invoice == null) {
            throw new NoSuchIncomeInvoiceException("There is no Income Invoice with id = " + id + " in Database");
        }
        return invoice;
    }

    @PostMapping("/income/save")
    @PreAuthorize("hasAuthority('WRITE')")
    public IncomeInvoice createIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.createIncomeInvoice(incomeInvoice);
    }

    @DeleteMapping("/income/delete/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public boolean deleteIncomeInvoiceById(@PathVariable Long id) {
        return service.deleteIncomeInvoiceById(id);
    }

    @PutMapping("/income/update")
    @PreAuthorize("hasAuthority('WRITE')")
    public IncomeInvoice updateIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.updateIncomeInvoice(incomeInvoice);
    }

}
