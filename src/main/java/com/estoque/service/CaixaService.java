package com.estoque.service;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;

import java.time.LocalDate;
import java.util.List;

public interface CaixaService {
    List<ResumoDeVendasDTO> consultar(LocalDate date, FormaPagamentoEnum formaPagamento, int page, int size, String sort, String order);
}
