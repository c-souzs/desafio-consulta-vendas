package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%') " +
            "AND obj.date BETWEEN :min AND :max ",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
    Page<Sale> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(s.name, SUM(sal.amount)) " +
            "FROM Seller s JOIN s.sales sal " +
            "WHERE sal.date BETWEEN :min AND :max " +
            "GROUP BY s.id")
    List<SellerMinDTO> searchSummaryAllSellers(LocalDate min, LocalDate max);
}
