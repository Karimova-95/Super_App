package ru.bell.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
public class IncomeInvoice extends Invoice  implements Serializable {
    public IncomeInvoice(Long id, Timestamp date, Integer no, String from, String to, Double sum, String description) {
        super(date, no, from, to, sum, description);
    }

    public IncomeInvoice(Timestamp date, int no, String from, String to, double sum, String description) {
        super(date, no, from, to, sum, description);
    }
}
