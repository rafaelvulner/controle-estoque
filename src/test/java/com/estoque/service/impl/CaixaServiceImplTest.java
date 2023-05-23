package com.estoque.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.repository.CaixaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CaixaServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CaixaServiceImplTest {
    @MockBean
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaServiceImpl caixaServiceImpl;

    /**
     * Method under test: {@link CaixaServiceImpl#consultarLancamentosPorData(LocalDate)}
     */
    @Test
    void testConsultarLancamentosPorData() {
        when(caixaRepository.findByDataPagamento(Mockito.<LocalDate>any())).thenReturn(new ArrayList<>());
        ResumoDeVendasDTO actualConsultarLancamentosPorDataResult = caixaServiceImpl
                .consultarLancamentosPorData(LocalDate.of(1970, 1, 1));
        assertEquals("1970-01-01", actualConsultarLancamentosPorDataResult.getDataDaVenda().toString());
        assertEquals(FormaPagamentoEnum.DEBITO_CREDITO, actualConsultarLancamentosPorDataResult.getFormaPagamento());
        assertEquals("0", actualConsultarLancamentosPorDataResult.getTotal().toString());
        verify(caixaRepository).findByDataPagamento(Mockito.<LocalDate>any());
    }


    /**
     * Method under test: {@link CaixaServiceImpl#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento() {
        when(caixaRepository.findByFormaPagamento(Mockito.<FormaPagamentoEnum>any())).thenReturn(new ArrayList<>());
        ResumoDeVendasDTO actualConsultarLancamentosPorFormaDePagamentoResult = caixaServiceImpl
                .consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum.DEBITO);
        assertNull(actualConsultarLancamentosPorFormaDePagamentoResult.getDataDaVenda());
        assertEquals(FormaPagamentoEnum.DEBITO, actualConsultarLancamentosPorFormaDePagamentoResult.getFormaPagamento());
        assertEquals("0", actualConsultarLancamentosPorFormaDePagamentoResult.getTotal().toString());
        verify(caixaRepository).findByFormaPagamento(Mockito.<FormaPagamentoEnum>any());
    }

    /**
     * Method under test: {@link CaixaServiceImpl#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento2() {
        when(caixaRepository.findByFormaPagamento(Mockito.<FormaPagamentoEnum>any())).thenReturn(new ArrayList<>());
        ResumoDeVendasDTO actualConsultarLancamentosPorFormaDePagamentoResult = caixaServiceImpl
                .consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum.CREDITO);
        assertNull(actualConsultarLancamentosPorFormaDePagamentoResult.getDataDaVenda());
        assertEquals(FormaPagamentoEnum.CREDITO, actualConsultarLancamentosPorFormaDePagamentoResult.getFormaPagamento());
        assertEquals("0", actualConsultarLancamentosPorFormaDePagamentoResult.getTotal().toString());
        verify(caixaRepository).findByFormaPagamento(Mockito.<FormaPagamentoEnum>any());
    }

    /**
     * Method under test: {@link CaixaServiceImpl#consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum)}
     */
    @Test
    void testConsultarLancamentosPorFormaDePagamento3() {
        when(caixaRepository.findByFormaPagamento(Mockito.<FormaPagamentoEnum>any())).thenReturn(new ArrayList<>());
        ResumoDeVendasDTO actualConsultarLancamentosPorFormaDePagamentoResult = caixaServiceImpl
                .consultarLancamentosPorFormaDePagamento(FormaPagamentoEnum.DEBITO_CREDITO);
        assertNull(actualConsultarLancamentosPorFormaDePagamentoResult.getDataDaVenda());
        assertEquals(FormaPagamentoEnum.DEBITO_CREDITO,
                actualConsultarLancamentosPorFormaDePagamentoResult.getFormaPagamento());
        assertEquals("0", actualConsultarLancamentosPorFormaDePagamentoResult.getTotal().toString());
        verify(caixaRepository).findByFormaPagamento(Mockito.<FormaPagamentoEnum>any());
    }


    /**
     * Method under test: {@link CaixaServiceImpl#consultarLancamentosDiario()}
     */
    @Test
    void testConsultarLancamentosDiario() {
        when(caixaRepository.findByDataPagamento(Mockito.<LocalDate>any())).thenReturn(new ArrayList<>());
        BigDecimal actualConsultarLancamentosDiarioResult = caixaServiceImpl.consultarLancamentosDiario();
        assertSame(actualConsultarLancamentosDiarioResult.ZERO, actualConsultarLancamentosDiarioResult);
        assertEquals("0", actualConsultarLancamentosDiarioResult.toString());
        verify(caixaRepository).findByDataPagamento(Mockito.<LocalDate>any());
    }


}

