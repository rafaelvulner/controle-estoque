package com.estoque.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProdutoDTO {

    private UUID sku;

    private String nome;

    private BigDecimal preco;

    public ProdutoDTO() {
        if (this.sku == null)
            this.sku = UUID.randomUUID();
    }
}
