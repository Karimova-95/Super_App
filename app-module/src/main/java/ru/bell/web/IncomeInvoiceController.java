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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Flux<IncomeInvoice> getIncomeInvoices() {
        return service.get();
    }

    @GetMapping("/income/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Mono<IncomeInvoice> getIncomeInvoicesById(@PathVariable Long id) {
        Mono<IncomeInvoice> invoice = service.get(id);
        if (invoice == null) {
            throw new NoSuchIncomeInvoiceException("There is no Income Invoice with id = " + id + " in Database");
        }
        return invoice;
    }

    @PostMapping("/income/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Mono<IncomeInvoice> createIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.create(incomeInvoice);
    }

    @DeleteMapping("/income/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Boolean> deleteIncomeInvoiceById(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/income/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<IncomeInvoice> updateIncomeInvoice(@RequestBody IncomeInvoice incomeInvoice) {
        return service.update(incomeInvoice);
    }

}
