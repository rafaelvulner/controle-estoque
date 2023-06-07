package com.estoque.service.impl;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.repository.CaixaRepository;
import com.estoque.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CaixaServiceImpl implements CaixaService {

    private final CaixaRepository caixaRepository;

    @Override
    public List<ResumoDeVendasDTO> consultar(LocalDate date, FormaPagamentoEnum formaPagamento, int page, int size, String sort, String order) {

        Page<Caixa> caixas;
        if (formaPagamento.equals(FormaPagamentoEnum.DEBITO_CREDITO)){
            caixas = this.caixaRepository.findCaixaByDataPagamento(
                    date, PageRequest.of(page, size, Sort.Direction.valueOf(sort), order));
        }else {
            caixas = this.caixaRepository.findCaixaByDataPagamentoAndFormaPagamento(
                    date, formaPagamento, PageRequest.of(page, size, Sort.Direction.valueOf(sort), order));
        }
        return caixas.stream().map(ResumoDeVendasDTO::new).collect(Collectors.toList());
    }
}
