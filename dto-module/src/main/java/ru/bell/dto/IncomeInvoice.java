package ru.bell.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
public class IncomeInvoice extends Invoice implements Serializable {

    private static final long serialVersionUID = 54844551659L;

    public IncomeInvoice(Long id, LocalDateTime date, Integer no, String from, String to, Double sum, String description) {
        super(id, date, no, from, to, sum, description);
    }

    public IncomeInvoice(LocalDateTime date, int no, String from, String to, double sum, String description) {
        super(date, no, from, to, sum, description);
    }
}
