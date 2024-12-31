package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate minDateLocal = minDate.isBlank() ?  LocalDate.now().minusYears(1L) : LocalDate.parse(minDate);

		LocalDate maxDateLocal = maxDate.isBlank() ?
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);

		Page<Sale> sales = repository.searchReport(minDateLocal, maxDateLocal, name, pageable);
		return sales.map(SaleMinDTO::new);
	}

	public List<SellerMinDTO> searchSummary(String minDate, String maxDate) {
		LocalDate minDateLocal = minDate.isBlank() ?  LocalDate.now().minusYears(1L) : LocalDate.parse(minDate);

		LocalDate maxDateLocal = maxDate.isBlank() ?
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);

		return repository.searchSummaryAllSellers(minDateLocal, maxDateLocal);
	}
}
