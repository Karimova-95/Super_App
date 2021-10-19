package ru.bell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Invoice implements Comparable<Invoice>{
    private Integer id;
    private Integer no;
    private String from;
    private String to;
    private Integer sum;
    private String description;

    public Invoice(Integer no, String from, String to, Integer sum, String description) {
        this.no = no;
        this.from = from;
        this.to = to;
        this.sum = sum;
        this.description = description;
    }

    public Invoice(Integer id, Integer no, String from, String to, Integer sum, String description) {
        this.id = id;
        this.no = no;
        this.from = from;
        this.to = to;
        this.sum = sum;
        this.description = description;
    }

    @Override
    public int compareTo(Invoice o) {
        return sum - o.sum;
    }
}