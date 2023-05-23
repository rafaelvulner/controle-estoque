package com.estoque.domain.dtos;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private UUID sku;

    private String nome;

    private BigDecimal preco;
}
