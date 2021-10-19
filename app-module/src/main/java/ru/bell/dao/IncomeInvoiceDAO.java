//package ru.bell.dao;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.annotation.Caching;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import ru.bell.dto.IncomeInvoice;
//
//import java.util.List;
//import java.util.Objects;
//
//@Repository
//@Slf4j
//@EnableCaching
//public class IncomeInvoiceDAO {
//    private final NamedParameterJdbcTemplate namedJdbcTemplate;
//    private final IncomeInvoiceMapper mapper;
//
//    public IncomeInvoiceDAO(NamedParameterJdbcTemplate namedJdbcTemplate) {
//        this.namedJdbcTemplate = namedJdbcTemplate;
//        this.mapper = new IncomeInvoiceMapper();
//    }
//
//    @Cacheable(value = "incomes", key = "'all'")
//    public List<IncomeInvoice> get() {
//        String sql = "select * from income_invoice";
//        return namedJdbcTemplate.query(sql, mapper);
//    }
//
//    @Caching(
//            put = @CachePut(value = "incomes", key = "#result.id"),
//            evict = @CacheEvict(value = "incomes", key = "'all'")
//    )
//    public IncomeInvoice create(IncomeInvoice doc) {
//        String sql = "INSERT INTO income_invoice (date, no, from_ca, to_ca, doc_sum, description) " +
//                "VALUES (:date, :no, :from, :to, :sum, :description)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
//                .addValue("date", doc.getDate())
//                .addValue("no", doc.getNo())
//                .addValue("from", doc.getFrom())
//                .addValue("to", doc.getTo())
//                .addValue("sum", doc.getSum())
//                .addValue("description", doc.getDescription());
//        namedJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
//        Objects.requireNonNull(keyHolder.getKey(), "id not created");
//        return get(keyHolder.getKey().longValue());
//    }
//
//    @Cacheable(value = "incomes", key = "#id")
//    public IncomeInvoice get(Long id) {
//        String sql = "SELECT * FROM income_invoice WHERE id = :id";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("id", id);
//        IncomeInvoice invoice;
//        try {
//            invoice = namedJdbcTemplate.queryForObject(sql, mapSqlParameterSource, mapper);
//        } catch (Exception ex) {
//            invoice = null;
//        }
//        return invoice;
//    }
//
//    @Caching(
//            evict = {@CacheEvict(value = "incomes", key = "#id"), @CacheEvict(value = "incomes", key = "'all'")}
//    )
//    public boolean delete(Long id) {
//        String sql = "DELETE FROM income_invoice WHERE id = :id";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("id", id);
//        return namedJdbcTemplate.update(sql, mapSqlParameterSource) > 0;
//    }
//
//    @Caching(
//            put = @CachePut(value = "incomes", key = "#p0"),
//            evict = @CacheEvict(value = "incomes", key = "'all'")
//    )
//    public IncomeInvoice update(IncomeInvoice doc) {
//        String sql = "UPDATE income_invoice SET date = :date, no = :no, from_ca = :from, to_ca = :to, doc_sum = :sum, description = :description WHERE id = :id";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
//                .addValue("date", doc.getDate())
//                .addValue("no", doc.getNo())
//                .addValue("from", doc.getFrom())
//                .addValue("to", doc.getTo())
//                .addValue("sum", doc.getSum())
//                .addValue("description", doc.getDescription())
//                .addValue("id", doc.getId());
//        if ((namedJdbcTemplate.update(sql, mapSqlParameterSource)) < 1) {
//            return null;
//        }
//        return get(doc.getId());
//    }
//}
