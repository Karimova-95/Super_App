package ru.bell.dao;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bell.dto.IncomeInvoice;
import ru.bell.service.IncomeInvoiceService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncomeInvoiceDAOwithMockitoTest {

    @Mock
    private IncomeInvoiceDAO dao;

    @InjectMocks
    private IncomeInvoiceService service;

    @Test
    public void getByIdTest() {
        IncomeInvoice expected = new IncomeInvoice(1L, new Timestamp(4577456L), 9684, "Usa", "Russia", 10963.55, "expected from Canada to Moscow");
        when(dao.get(anyLong())).thenReturn(expected);
        IncomeInvoice actual = service.getIncomeInvoiceById(1L);

        assertNotNull(actual);
        assertEquals(expected.getNo(), actual.getNo());
        assertEquals(expected.getFrom(), actual.getFrom());
        assertEquals(expected.getTo(), actual.getTo());
        assertEquals(expected.getSum(), actual.getSum());
        assertEquals(expected.getDescription(), actual.getDescription());

        verify(dao).get(1L);
    }

    @Test
    public void getAllTest() {
        List<IncomeInvoice> list = new ArrayList<>();

        IncomeInvoice expectedOne = new IncomeInvoice(1L, new Timestamp(4577456L), 9684, "Usa", "Russia", 10963.55, "Invoice from Canada to Moscow");
        IncomeInvoice expectedTwo = new IncomeInvoice(2L, new Timestamp(4588456L), 8684, "Ukraine", "China", 13963.55, "Invoice from Ukraine to China");
        IncomeInvoice expectedThree = new IncomeInvoice(3L, new Timestamp(4599456L), 7684, "Norway", "Scotland", 11963.55, "Invoice from Norway to Scotland");

        list.add(expectedOne);
        list.add(expectedTwo);
        list.add(expectedThree);

        when(dao.get()).thenReturn(list);

        List<IncomeInvoice> actual = service.getIncomeInvoices();

        assertEquals(3, actual.size());
        verify(dao).get();
    }

    @Test
    public void createTest() {
        IncomeInvoice expected = new IncomeInvoice(new Timestamp(4577456L), 9684, "Usa", "Russia", 10963.55, "Invoice from Canada to Moscow");
        service.createIncomeInvoice(expected);
        verify(dao).create(expected);
    }

    @Test
    @SneakyThrows
    public void answerTest() {
        when(dao.get(anyLong())).thenAnswer(I -> getDummyInvoice());
        IncomeInvoice created = getDummyInvoice();
        Thread.sleep(1);
        IncomeInvoice dummy = service.getIncomeInvoiceById(1L);
        assertTrue(dummy.getDate().after(created.getDate()));
    }

    private IncomeInvoice getDummyInvoice() {
        return new IncomeInvoice(
                1L,
                Timestamp.valueOf(LocalDateTime.now()),
                9684,
                "Usa",
                "Russia",
                10963.55,
                "Invoice from Canada to Moscow"
        );
    }
}