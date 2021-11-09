package ru.bell.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.bell.dto.IncomeInvoice;

@Repository
public interface IncomeInvoiceRepository extends ReactiveSortingRepository<IncomeInvoice, Long> {

    Mono<Boolean> deleteIncomeInvoiceById(Long id);

//    @Query("DELETE FROM income_invoice WHERE id = :id")
//    Mono<Boolean> doSomething(@Param(value = "id") Long id);

}
