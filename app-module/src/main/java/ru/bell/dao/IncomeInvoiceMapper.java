package ru.bell.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.bell.dto.IncomeInvoice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeInvoiceMapper implements RowMapper<IncomeInvoice> {

    @Override
    public IncomeInvoice mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        IncomeInvoice incomeInvoice = new IncomeInvoice();
        incomeInvoice.setId(resultSet.getLong("id"));
        incomeInvoice.setDate(resultSet.getTimestamp("date"));
        incomeInvoice.setNo(resultSet.getInt("no"));
        incomeInvoice.setFrom(resultSet.getString("from_ca"));
        incomeInvoice.setTo(resultSet.getString("to_ca"));
        incomeInvoice.setSum(resultSet.getDouble("doc_sum"));
        incomeInvoice.setDescription(resultSet.getString("description"));
        return incomeInvoice;
    }
}