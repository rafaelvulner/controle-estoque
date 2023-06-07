package com.estoque.domain.dtos;

import com.estoque.domain.Caixa;
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

    private LocalDate dataPagamento;

    private BigDecimal total;

    private FormaPagamentoEnum formaPagamento;

    public ResumoDeVendasDTO(Caixa caixa) {
        this.dataPagamento = caixa.getDataPagamento();
        this.total = caixa.getTotal();
        this.formaPagamento = caixa.getFormaPagamento();
    }
}
