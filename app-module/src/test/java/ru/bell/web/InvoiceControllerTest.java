package ru.bell.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import ru.bell.App;
import ru.bell.dto.IncomeInvoice;
import ru.bell.security.Role;
import ru.bell.service.IncomeInvoiceService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.bell.web.AbstractControllerTest.asJsonString;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@WebMvcTest(value = IncomeInvoiceController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class InvoiceControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncomeInvoiceService service;

    @WithMockUser(value = "USER")
    @Test
    void getAll() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp DATE = new Timestamp(time);
        IncomeInvoice invoice1 = new IncomeInvoice(1L, DATE, 745, "AAA", "BBB", 745.25, "some description");
        IncomeInvoice invoice2 = new IncomeInvoice(2L, DATE, 358, "CCC", "DDD", 968.00, "some description");
        List<IncomeInvoice> list = new ArrayList<>();
        list.add(invoice1);
        list.add(invoice2);
        //Given
        when(service.getIncomeInvoices()).thenReturn(list);

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        //Then
                        .get("/income"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].from").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sum").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @WithMockUser(value = "USER")
    @Test
    void getById() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2000");
        long time = date.getTime();
        Timestamp DATE = new Timestamp(time);
        IncomeInvoice invoice = new IncomeInvoice(1L, DATE, 745, "AAA", "BBB", 745.25, "some description");
        when(service.getIncomeInvoiceById(any())).thenReturn(invoice);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/income/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.no").value(745));
    }

    @WithMockUser(value = "USER")
    @Test
    void create() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2020");
        long time = date.getTime();
        Timestamp DATE = new Timestamp(time);
        IncomeInvoice invoice = new IncomeInvoice(1L, DATE, 745, "AAA", "BBB", 745.25, "some description");
        when(service.createIncomeInvoice(invoice)).thenReturn(invoice);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/income/save", invoice)
                        .content(asJsonString(invoice))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.from").value("AAA"));
    }


    @WithMockUser(value = "USER")
    @Test
    void deleteInvoice() throws Exception {
        when(service.deleteIncomeInvoiceById(any())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/income/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}