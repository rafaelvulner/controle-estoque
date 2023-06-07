package com.estoque.domain.dtos;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResumoDeVendasDTOTest {
    /**
     * Method under test: {@link ResumoDeVendasDTO#ResumoDeVendasDTO(Caixa)}
     */
    @Test
    void testConstructor() {
        ResumoDeVendasDTO actualResumoDeVendasDTO = new ResumoDeVendasDTO(new Caixa());
        assertNull(actualResumoDeVendasDTO.getDataPagamento());
        assertNull(actualResumoDeVendasDTO.getTotal());
        assertNull(actualResumoDeVendasDTO.getFormaPagamento());
    }

    /**
     * Method under test: {@link ResumoDeVendasDTO#ResumoDeVendasDTO(Caixa)}
     */
    @Test
    void testConstructor3() {
        Caixa caixa = mock(Caixa.class);
        when(caixa.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(caixa.getTotal()).thenReturn(valueOfResult);
        when(caixa.getDataPagamento()).thenReturn(LocalDate.of(1970, 1, 1));
        ResumoDeVendasDTO actualResumoDeVendasDTO = new ResumoDeVendasDTO(caixa);
        assertEquals("1970-01-01", actualResumoDeVendasDTO.getDataPagamento().toString());
        BigDecimal expectedTotal = BigDecimal.ONE;
        BigDecimal total = actualResumoDeVendasDTO.getTotal();
        assertSame(expectedTotal, total);
        assertEquals(FormaPagamentoEnum.DEBITO, actualResumoDeVendasDTO.getFormaPagamento());
        assertEquals("1", total.toString());
        verify(caixa).getFormaPagamento();
        verify(caixa).getTotal();
        verify(caixa).getDataPagamento();
    }
}

