package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerMinDTO> searchSummary(String minDate, String maxDate) {
        LocalDate minDateLocal = minDate.isBlank() ?  LocalDate.now().minusYears(1L) : LocalDate.parse(minDate);

        LocalDate maxDateLocal = maxDate.isBlank() ?
                LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);

        return sellerRepository.searchSummaryAllSellers(minDateLocal, maxDateLocal);
    }
}
