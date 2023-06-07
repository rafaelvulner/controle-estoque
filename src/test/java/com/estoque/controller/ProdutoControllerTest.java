package com.estoque.controller;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.repository.ProdutoRepository;
import com.estoque.service.ProdutoService;
import com.estoque.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {
    /**
     * Method under test: {@link ProdutoController#cadastrar(List)}
     */
    @Test
    void testCadastrar() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        ArrayList<Produto> produtoList = new ArrayList<>();
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(produtoList);
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());
        ResponseEntity<List<ProdutoDTO>> actualCadastrarResult = produtoController.cadastrar(new ArrayList<>());
        assertEquals(produtoList, actualCadastrarResult.getBody());

        assertEquals(HttpStatus.OK, actualCadastrarResult.getStatusCode());
        assertTrue(actualCadastrarResult.getHeaders().isEmpty());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#cadastrar(List)}
     */
    @Test
    void testCadastrar3() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());

        ArrayList<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(new ProdutoDTO());
        ResponseEntity<List<ProdutoDTO>> actualCadastrarResult = produtoController.cadastrar(produtos);
        assertTrue(actualCadastrarResult.hasBody());
        assertTrue(actualCadastrarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCadastrarResult.getStatusCode());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#cadastrar(List)}
     */
    @Test
    void testCadastrar4() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());

        ArrayList<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(new ProdutoDTO());
        produtos.add(new ProdutoDTO());
        ResponseEntity<List<ProdutoDTO>> actualCadastrarResult = produtoController.cadastrar(produtos);
        assertTrue(actualCadastrarResult.hasBody());
        assertTrue(actualCadastrarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCadastrarResult.getStatusCode());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#cadastrar(List)}
     */
    @Test
    void testCadastrar6() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());
        ProdutoDTO produtoDTO = mock(ProdutoDTO.class);
        when(produtoDTO.getNome()).thenReturn("Nome");
        when(produtoDTO.getPreco()).thenReturn(BigDecimal.valueOf(1L));
        when(produtoDTO.getSku()).thenReturn(UUID.randomUUID());

        ArrayList<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(produtoDTO);
        ResponseEntity<List<ProdutoDTO>> actualCadastrarResult = produtoController.cadastrar(produtos);
        assertTrue(actualCadastrarResult.hasBody());
        assertTrue(actualCadastrarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCadastrarResult.getStatusCode());
        verify(produtoRepository).saveAll(Mockito.any());
        verify(produtoDTO).getNome();
        verify(produtoDTO).getPreco();
        verify(produtoDTO).getSku();
    }

    /**
     * Method under test: {@link ProdutoController#excluir(Long)}
     */
    @Test
    void testExcluir() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        doNothing().when(produtoRepository).delete(Mockito.any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Produto()));
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ResponseEntity<Void> actualExcluirResult = (new ProdutoController(produtoService, new ModelMapper())).excluir(1L);
        assertNull(actualExcluirResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualExcluirResult.getStatusCode());
        assertTrue(actualExcluirResult.getHeaders().isEmpty());
        verify(produtoRepository).findById(Mockito.<Long>any());
        verify(produtoRepository).delete(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#buscar(UUID)}
     */
    @Test
    void testBuscar() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findBySku(Mockito.any())).thenReturn(Optional.of(new Produto()));
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());
        ResponseEntity<ProdutoDTO> actualBuscarResult = produtoController.buscar(UUID.randomUUID());
        assertTrue(actualBuscarResult.hasBody());
        assertTrue(actualBuscarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarResult.getStatusCode());
        ProdutoDTO body = actualBuscarResult.getBody();
        assert body != null;
        assertNull(body.getPreco());
        assertNull(body.getNome());
        assertNull(body.getSku());
        verify(produtoRepository).findBySku(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#buscar(UUID)}
     */
    @Test
    void testBuscar2() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        UUID sku = UUID.randomUUID();
        BigDecimal preco = BigDecimal.valueOf(1L);
        when(produtoRepository.findBySku(Mockito.any()))
                .thenReturn(Optional.of(new Produto(1L, sku, "Nome", preco)));
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());
        ResponseEntity<ProdutoDTO> actualBuscarResult = produtoController.buscar(UUID.randomUUID());
        assertTrue(actualBuscarResult.hasBody());
        assertTrue(actualBuscarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarResult.getStatusCode());
        BigDecimal expectedPreco = BigDecimal.ONE;
        ProdutoDTO body = actualBuscarResult.getBody();
        assert body != null;
        BigDecimal preco2 = body.getPreco();
        assertSame(expectedPreco, preco2);
        assertEquals("Nome", body.getNome());
        assertSame(sku, body.getSku());
        assertEquals("1", preco2.toString());
        verify(produtoRepository).findBySku(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoController#buscar(UUID)}
     */
    @Test
    void testBuscar3() {

        Produto produto = mock(Produto.class);
        when(produto.getNome()).thenReturn("Nome");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(produto.getPreco()).thenReturn(valueOfResult);
        UUID randomUUIDResult = UUID.randomUUID();
        when(produto.getSku()).thenReturn(randomUUIDResult);
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findBySku(Mockito.any())).thenReturn(Optional.of(produto));
        ProdutoServiceImpl produtoService = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ProdutoController produtoController = new ProdutoController(produtoService, new ModelMapper());
        ResponseEntity<ProdutoDTO> actualBuscarResult = produtoController.buscar(UUID.randomUUID());
        assertTrue(actualBuscarResult.hasBody());
        assertTrue(actualBuscarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarResult.getStatusCode());
        BigDecimal expectedPreco = BigDecimal.ONE;
        ProdutoDTO body = actualBuscarResult.getBody();
        assert body != null;
        BigDecimal preco = body.getPreco();
        assertSame(expectedPreco, preco);
        assertEquals("Nome", body.getNome());
        assertSame(randomUUIDResult, body.getSku());
        assertEquals("1", preco.toString());
        verify(produtoRepository).findBySku(Mockito.any());
        verify(produto).getNome();
        verify(produto).getPreco();
        verify(produto).getSku();
    }

    /**
     * Method under test: {@link ProdutoController#buscar(UUID)}
     */
    @Test
    void testBuscar6() {

        ProdutoService produtoService = mock(ProdutoService.class);
        when(produtoService.buscarPorUUID(Mockito.any())).thenReturn(new Produto());
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ProdutoDTO>>any())).thenReturn(new ProdutoDTO());
        ProdutoController produtoController = new ProdutoController(produtoService, modelMapper);
        ResponseEntity<ProdutoDTO> actualBuscarResult = produtoController.buscar(UUID.randomUUID());
        assertTrue(actualBuscarResult.hasBody());
        assertTrue(actualBuscarResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarResult.getStatusCode());
        verify(produtoService).buscarPorUUID(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoController#buscarTodos(int, int, String, String)}
     */
    @Test
    void testBuscarTodos2() {

        ProdutoServiceImpl produtoService = mock(ProdutoServiceImpl.class);
        when(produtoService.buscarTodos(anyInt(), anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());
        ResponseEntity<List<ProdutoDTO>> actualBuscarTodosResult = (new ProdutoController(produtoService,
                new ModelMapper())).buscarTodos(1, 3, "Sort", "Order");
        assertTrue(actualBuscarTodosResult.hasBody());
        assertEquals(HttpStatus.OK, actualBuscarTodosResult.getStatusCode());
        assertTrue(actualBuscarTodosResult.getHeaders().isEmpty());
        verify(produtoService).buscarTodos(anyInt(), anyInt(), Mockito.any(), Mockito.any());
    }
}

