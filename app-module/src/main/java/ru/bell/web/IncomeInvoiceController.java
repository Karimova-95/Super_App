package ru.bell.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;
import ru.bell.exception.NoSuchIncomeInvoiceException;
import ru.bell.service.IncomeInvoiceService;

@RestController
@EnableReactiveMethodSecurity
public class IncomeInvoiceController {
    private final IncomeInvoiceService service;

    public IncomeInvoiceController(IncomeInvoiceService service) {
        this.service = service;
    }

    @GetMapping("/income")
    @PreAuthorize("hasRole('READ')")
    public Flux<IncomeInvoice> getIncomeInvoices() {
        return service.getIncomeInvoices();
    }

    @GetMapping("/income/{id}")
    @PreAuthorize("hasRole('READ')")
    public Mono<IncomeInvoice> getIncomeInvoicesById(@PathVariable Integer id) {
        Mono<IncomeInvoice> invoice = service.getIncomeInvoiceById(id);
        if (invoice == null) {
            throw new NoSuchIncomeInvoiceException("There is no Income Invoice with id = " + id + " in Database");
        }
        return invoice;
    }

    @PostMapping("/income/save")
    @PreAuthorize("hasRole('WRITE')")
    public Mono<IncomeInvoice> createIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.create(incomeInvoice);
    }

    @DeleteMapping("/income/delete/{id}")
    @PreAuthorize("hasRole('WRITE')")
    public Mono<Void> deleteIncomeInvoiceById(@PathVariable Integer id) {
        return service.deleteIncomeInvoiceById(id);
    }

    @PutMapping("/income/update")
    @PreAuthorize("hasRole('WRITE')")
    public Flux<IncomeInvoice> updateIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.updateIncomeInvoice(incomeInvoice);
    }

}
