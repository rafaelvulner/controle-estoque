package com.estoque.service;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CaixaService {

    ResumoDeVendasDTO consultarLancamentosPorData(LocalDate date);
   ResumoDeVendasDTO consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum formaPagamento);

    BigDecimal consultarLancamentosDiario();
}
