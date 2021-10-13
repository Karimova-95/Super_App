package ru.bell.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bell.dto.IncomeInvoice;

public class AbstractControllerTest {
    public static String asJsonString(IncomeInvoice invoice) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(invoice);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}