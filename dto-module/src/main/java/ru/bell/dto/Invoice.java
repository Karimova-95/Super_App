package ru.bell.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Long id;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime date;
    private Integer no;
    private String from;
    private String to;
    private Double sum;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public Invoice(LocalDateTime date, Integer no, String from, String to, Double sum, String description) {
        this.date = date;
        this.no = no;
        this.from = from;
        this.to = to;
        this.sum = sum;
        this.description = description;
    }
}