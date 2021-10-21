package ru.bell.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class IncomeInvoice extends Invoice  implements Serializable {
    public IncomeInvoice(Integer id, Integer no, String from, String to, Integer sum, String description) {
        super(no, from, to, sum, description);
    }

    public IncomeInvoice(int no, String from, String to, Integer sum, String description) {
        super(no, from, to, sum, description);
    }
}
