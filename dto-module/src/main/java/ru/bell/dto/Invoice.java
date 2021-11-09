package ru.bell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    private Long id;
    private LocalDateTime date;
    private Integer no;
    private String from_ca;
    private String to_ca;
    private Double doc_sum;
    private String description;

    public Invoice(LocalDateTime date, Integer no, String from_ca, String to_ca, Double doc_sum, String description) {
        this.date = date;
        this.no = no;
        this.from_ca = from_ca;
        this.to_ca = to_ca;
        this.doc_sum = doc_sum;
        this.description = description;
    }
}