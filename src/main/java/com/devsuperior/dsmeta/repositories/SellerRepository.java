package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(s.name, SUM(sal.amount)) " +
            "FROM Seller s JOIN s.sales sal " +
            "WHERE sal.date BETWEEN :min AND :max " +
            "GROUP BY s.id")
    List<SellerMinDTO> searchSummaryAllSellers(LocalDate min, LocalDate max);
}
