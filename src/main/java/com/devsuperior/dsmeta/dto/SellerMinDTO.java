package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {
    private String sellerName;
    private Double total;

    public SellerMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SellerMinDTO(Seller entity) {
        sellerName = entity.getName();
        total = entity.getSales().stream()
                .map(Sale::getAmount)
                .reduce(0.0, Double::sum);
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
