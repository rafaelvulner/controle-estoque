package com.estoque.domain.dtos;

import com.estoque.domain.FormaPagamentoEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumoDeVendasDTO {

    private LocalDate dataDaVenda;

    private BigDecimal total;

    private FormaPagamentoEnum formaPagamento;

}
