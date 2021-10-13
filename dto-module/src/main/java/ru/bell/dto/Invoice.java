package ru.bell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Long id;
    private Timestamp date;
    private Integer no;
    private String from;
    private String to;
    private Double sum;
    private String description;

    public Invoice(Timestamp date, Integer no, String from, String to, Double sum, String description) {
        this.date = date;
        this.no = no;
        this.from = from;
        this.to = to;
        this.sum = sum;
        this.description = description;
    }

}