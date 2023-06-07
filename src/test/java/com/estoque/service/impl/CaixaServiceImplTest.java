package com.estoque.service.impl;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.repository.CaixaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CaixaServiceImplTest {

    /**
     * Method under test: {@link CaixaServiceImpl#consultar(LocalDate, FormaPagamentoEnum, int, int, String, String)}
     */
    @Test
    void testConsultar() {
        CaixaRepository caixaRepository = mock(CaixaRepository.class);
        List<Caixa> caixa = new ArrayList<>();
        when(caixaRepository.findCaixaByDataPagamento(any(), any()))
                .thenReturn(new PageImpl<>(caixa));
        CaixaServiceImpl caixaService = new CaixaServiceImpl(caixaRepository);
        List<ResumoDeVendasDTO> consultar = caixaService.consultar(LocalDate.of(1970, 1, 1), FormaPagamentoEnum.DEBITO_CREDITO, 1, 10, "ASC", "dataPagamento");
        Assertions.assertNotNull(consultar);

    }

    /**
     * Method under test: {@link CaixaServiceImpl#consultar(LocalDate, FormaPagamentoEnum, int, int, String, String)}
     */
    @Test
    void testConsultar2() {
        CaixaRepository caixaRepository = mock(CaixaRepository.class);
        List<Caixa> caixa = new ArrayList<>();
        when(caixaRepository.findCaixaByDataPagamentoAndFormaPagamento(any(), any(), any()))
                .thenReturn(new PageImpl<>(caixa));

        CaixaServiceImpl caixaService = new CaixaServiceImpl(caixaRepository);
        List<ResumoDeVendasDTO> consultar = caixaService.consultar(LocalDate.of(1970, 1, 1), FormaPagamentoEnum.DEBITO, 1, 10, "ASC", "dataPagamento");
        Assertions.assertNotNull(consultar);

    }
}

