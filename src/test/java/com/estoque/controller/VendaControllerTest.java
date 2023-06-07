package com.estoque.controller;

import com.estoque.domain.Venda;
import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.repository.CaixaRepository;
import com.estoque.repository.ProdutoRepository;
import com.estoque.repository.VendaRepository;
import com.estoque.service.impl.ProdutoServiceImpl;
import com.estoque.service.impl.VendaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendaControllerTest {

    /**
     * Method under test: {@link VendaController#cadastrar(VendaDTO)}
     */
    @Test
    void testCadastrar2() {

        VendaServiceImpl vendaService = mock(VendaServiceImpl.class);
        when(vendaService.cadastrar(Mockito.any())).thenReturn(new VendaDTO());
        VendaController vendaController = new VendaController(vendaService);
        ResponseEntity<VendaDTO> actualCadastrarResult = vendaController.cadastrar(new VendaDTO());
        assertTrue(actualCadastrarResult.hasBody());
        assertTrue(actualCadastrarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCadastrarResult.getStatusCode());
        verify(vendaService).cadastrar(Mockito.any());
    }

    /**
     * Method under test: {@link VendaController#buscar(Long)}
     */
    @Test
    void testBuscar() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Venda()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        ResponseEntity<VendaDTO> actualBuscarResult = (new VendaController(new VendaServiceImpl(vendaRepository,
                modelMapper, new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))))
                .buscar(1L);
        assertTrue(actualBuscarResult.hasBody());
        assertTrue(actualBuscarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarResult.getStatusCode());
        VendaDTO body = actualBuscarResult.getBody();
        assert body != null;
        assertNull(body.getProdutos());
        assertFalse(body.isAtivo());
        assertNull(body.getFormaPagamento());
        assertNull(body.getDataDaVenda());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaController#cancelar(Long)}
     */
    @Test
    void testCancelar() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Venda()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        ResponseEntity<CancelamentoVendaDTO> actualCancelarResult = (new VendaController(
                new VendaServiceImpl(vendaRepository, modelMapper,
                        new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class)))).cancelar(1L);
        assertTrue(actualCancelarResult.hasBody());
        assertTrue(actualCancelarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCancelarResult.getStatusCode());
        CancelamentoVendaDTO body = actualCancelarResult.getBody();
        assert body != null;
        assertEquals("Venda N: null cancelado!", body.getMensagem());
        assertNull(body.getFormaPagamento());
        assertNull(body.getProdutos());
        verify(vendaRepository).save(Mockito.any());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }
}

