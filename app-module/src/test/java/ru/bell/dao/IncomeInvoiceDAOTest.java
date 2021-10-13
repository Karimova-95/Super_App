package ru.bell.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bell.dto.IncomeInvoice;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class IncomeInvoiceDAOTest {

    @Autowired
    private IncomeInvoiceDAO dao;

    @BeforeAll
    @Sql(scripts = "classpath:/data.sql")
    public static void init() {}

    @Test
    public void deleteTest() {
        List<IncomeInvoice> listBeforeDelete = dao.get();
        assertFalse(dao.delete(50L));
        assertTrue(dao.delete(1L));
        assertEquals(listBeforeDelete.size() - 1, dao.get().size());
    }

    @Test
    public void createTest() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp date1 = new Timestamp(time);
        IncomeInvoice expected = new IncomeInvoice(date1, 7563, "Kiev", "Minsk", 697.13, "some description");
        IncomeInvoice result = dao.create(expected);
        assertNotNull(result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getNo(), result.getNo());
        assertEquals(expected.getFrom(), result.getFrom());
        assertEquals(expected.getTo(), result.getTo());
        assertEquals(expected.getSum(), result.getSum());
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    void getAllTest() {
        List<IncomeInvoice> list = dao.get();
        assertEquals(4, list.size());
    }

    @Test
    void updateTest() {
        IncomeInvoice beforeUpdate = dao.get(1L);
        beforeUpdate.setDescription("Super new Description");
        IncomeInvoice afterUpdate = dao.update(beforeUpdate);

        assertEquals(beforeUpdate.getId(), afterUpdate.getId());
        assertEquals(beforeUpdate.getSum(), afterUpdate.getSum());
        assertEquals(beforeUpdate.getTo(), afterUpdate.getTo());
        assertEquals(beforeUpdate.getNo(), afterUpdate.getNo());
        assertEquals(beforeUpdate.getDate(), afterUpdate.getDate());
        assertEquals(beforeUpdate.getFrom(), afterUpdate.getFrom());
        assertEquals(beforeUpdate.getDescription(), afterUpdate.getDescription());
    }
}