package com.estoque.service.impl;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.repository.CaixaRepository;
import com.estoque.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CaixaServiceImpl implements CaixaService {

    private final CaixaRepository caixaRepository;

    @Override
    public ResumoDeVendasDTO consultarLancamentosPorData(LocalDate date) {
        return ResumoDeVendasDTO.builder()
                .total(this.caixaRepository.findByDataPagamento(date).stream().map(Caixa::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .dataDaVenda(date)
                .formaPagamento(FormaPagamentoEnum.DEBITO_CREDITO)
                .build();
    }

    @Override
    public ResumoDeVendasDTO consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum formaPagamento) {
        return ResumoDeVendasDTO.builder()
                .total(this.caixaRepository.findByFormaPagamento(formaPagamento)
                        .stream().map(Caixa::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .formaPagamento(formaPagamento)
                .build();
    }

    @Override
    public BigDecimal consultarLancamentosDiario() {
        return this.caixaRepository.findByDataPagamento(LocalDate.now()).stream().map(Caixa::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
