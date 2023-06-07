package com.estoque.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.service.CaixaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CaixaControllerTest {

    /**
     * Method under test: {@link CaixaController#consultar(LocalDate, FormaPagamentoEnum, int, int, String, String)}
     */
    @Test
    void testConsultar() {

        CaixaService caixaService = mock(CaixaService.class);
        when(caixaService.consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        CaixaController caixaController = new CaixaController(caixaService);
        ResponseEntity<List<ResumoDeVendasDTO>> actualConsultarResult = caixaController.consultar(LocalDate.of(1970, 1, 1),
                FormaPagamentoEnum.DEBITO, 1, 3, "Sort", "Order");
        assertTrue(actualConsultarResult.hasBody());
        assertEquals(HttpStatus.OK, actualConsultarResult.getStatusCode());
        assertTrue(actualConsultarResult.getHeaders().isEmpty());
        verify(caixaService).consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link CaixaController#consultar(LocalDate, FormaPagamentoEnum, int, int, String, String)}
     */
    @Test
    void testConsultar2() {

        CaixaService caixaService = mock(CaixaService.class);
        when(caixaService.consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        CaixaController caixaController = new CaixaController(caixaService);
        ResponseEntity<List<ResumoDeVendasDTO>> actualConsultarResult = caixaController
                .consultar(LocalDate.of(1970, 1, 1), FormaPagamentoEnum.CREDITO, 1, 3, "Sort", "Order");
        assertTrue(actualConsultarResult.hasBody());
        assertEquals(HttpStatus.OK, actualConsultarResult.getStatusCode());
        assertTrue(actualConsultarResult.getHeaders().isEmpty());
        verify(caixaService).consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link CaixaController#consultar(LocalDate, FormaPagamentoEnum, int, int, String, String)}
     */
    @Test
    void testConsultar3() {

        CaixaService caixaService = mock(CaixaService.class);
        when(caixaService.consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        CaixaController caixaController = new CaixaController(caixaService);
        ResponseEntity<List<ResumoDeVendasDTO>> actualConsultarResult = caixaController
                .consultar(LocalDate.of(1970, 1, 1), FormaPagamentoEnum.DEBITO_CREDITO, 1, 3, "Sort", "Order");
        assertTrue(actualConsultarResult.hasBody());
        assertEquals(HttpStatus.OK, actualConsultarResult.getStatusCode());
        assertTrue(actualConsultarResult.getHeaders().isEmpty());
        verify(caixaService).consultar(Mockito.any(), Mockito.any(), anyInt(), anyInt(),
                Mockito.any(), Mockito.any());
    }
}

