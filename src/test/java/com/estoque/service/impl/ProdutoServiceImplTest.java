package com.estoque.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProdutoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProdutoServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoServiceImpl produtoServiceImpl;

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProduto(ProdutoDTO)}
     */
    @Test
    void testCadastrarProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        when(produtoRepository.save(Mockito.<Produto>any())).thenReturn(produto);

        Produto produto2 = new Produto();
        produto2.setId(1L);
        produto2.setNome("Nome");
        produto2.setPreco(BigDecimal.valueOf(42L));
        produto2.setSku(UUID.randomUUID());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Produto>>any())).thenReturn(produto2);
        ProdutoDTO produtoDTO = new ProdutoDTO();
        assertSame(produtoDTO, produtoServiceImpl.cadastrarProduto(produtoDTO));
        verify(produtoRepository).save(Mockito.<Produto>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Produto>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProduto(ProdutoDTO)}
     */
    @Test
    void testCadastrarProduto2() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        when(produtoRepository.save(Mockito.<Produto>any())).thenReturn(produto);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Produto>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.cadastrarProduto(new ProdutoDTO()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Produto>>any());
    }


    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProdutos(List)}
     */
    @Test
    void testCadastrarProdutos() {
        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        List<ProdutoDTO> actualCadastrarProdutosResult = produtoServiceImpl.cadastrarProdutos(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarProdutosResult);
        assertTrue(actualCadastrarProdutosResult.isEmpty());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProdutos(List)}
     */
    @Test
    void testCadastrarProdutos2() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        when(produtoRepository.save(Mockito.<Produto>any())).thenReturn(produto);

        Produto produto2 = new Produto();
        produto2.setId(1L);
        produto2.setNome("Nome");
        produto2.setPreco(BigDecimal.valueOf(42L));
        produto2.setSku(UUID.randomUUID());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Produto>>any())).thenReturn(produto2);

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        List<ProdutoDTO> actualCadastrarProdutosResult = produtoServiceImpl.cadastrarProdutos(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarProdutosResult);
        assertEquals(1, actualCadastrarProdutosResult.size());
        verify(produtoRepository).save(Mockito.<Produto>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Produto>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProdutos(List)}
     */
    @Test
    void testCadastrarProdutos3() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        when(produtoRepository.save(Mockito.<Produto>any())).thenReturn(produto);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Produto>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.cadastrarProdutos(produtoDTOS));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Produto>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrarProdutos(List)}
     */
    @Test
    void testCadastrarProdutos4() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        when(produtoRepository.save(Mockito.<Produto>any())).thenReturn(produto);

        Produto produto2 = new Produto();
        produto2.setId(1L);
        produto2.setNome("Nome");
        produto2.setPreco(BigDecimal.valueOf(42L));
        produto2.setSku(UUID.randomUUID());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Produto>>any())).thenReturn(produto2);

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        produtoDTOS.add(new ProdutoDTO());
        List<ProdutoDTO> actualCadastrarProdutosResult = produtoServiceImpl.cadastrarProdutos(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarProdutosResult);
        assertEquals(2, actualCadastrarProdutosResult.size());
        verify(produtoRepository, atLeast(1)).save(Mockito.<Produto>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Produto>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarProduto(UUID)}
     */
    @Test
    void testBuscarProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        when(produtoRepository.findBySku(Mockito.<UUID>any())).thenReturn(ofResult);
        ProdutoDTO produtoDTO = new ProdutoDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any())).thenReturn(produtoDTO);
        assertSame(produtoDTO, produtoServiceImpl.buscarProduto(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.<UUID>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarProduto(UUID)}
     */
    @Test
    void testBuscarProduto2() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        when(produtoRepository.findBySku(Mockito.<UUID>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.buscarProduto(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.<UUID>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarTodosProdutos()}
     */
    @Test
    void testBuscarTodosProdutos() {
        when(produtoRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(produtoServiceImpl.buscarTodosProdutos().isEmpty());
        verify(produtoRepository).findAll();
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarTodosProdutos()}
     */
    @Test
    void testBuscarTodosProdutos2() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());

        ArrayList<Produto> produtoList = new ArrayList<>();
        produtoList.add(produto);
        when(produtoRepository.findAll()).thenReturn(produtoList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any())).thenReturn(new ProdutoDTO());
        assertEquals(1, produtoServiceImpl.buscarTodosProdutos().size());
        verify(produtoRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarTodosProdutos()}
     */
    @Test
    void testBuscarTodosProdutos3() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("com.estoque.domain.Produto");
        produto2.setPreco(BigDecimal.valueOf(42L));
        produto2.setSku(UUID.randomUUID());

        ArrayList<Produto> produtoList = new ArrayList<>();
        produtoList.add(produto2);
        produtoList.add(produto);
        when(produtoRepository.findAll()).thenReturn(produtoList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any())).thenReturn(new ProdutoDTO());
        assertEquals(2, produtoServiceImpl.buscarTodosProdutos().size());
        verify(produtoRepository).findAll();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarTodosProdutos()}
     */
    @Test
    void testBuscarTodosProdutos4() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());

        ArrayList<Produto> produtoList = new ArrayList<>();
        produtoList.add(produto);
        when(produtoRepository.findAll()).thenReturn(produtoList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.buscarTodosProdutos());
        verify(produtoRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#excluirProduto(Long)}
     */
    @Test
    void testExcluirProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        doNothing().when(produtoRepository).delete(Mockito.<Produto>any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        produtoServiceImpl.excluirProduto(1L);
        verify(produtoRepository).findById(Mockito.<Long>any());
        verify(produtoRepository).delete(Mockito.<Produto>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#excluirProduto(Long)}
     */
    @Test
    void testExcluirProduto2() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        doThrow(new VendaNotFoundException("An error occurred")).when(produtoRepository).delete(Mockito.<Produto>any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.excluirProduto(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
        verify(produtoRepository).delete(Mockito.<Produto>any());
    }


    /**
     * Method under test: {@link ProdutoServiceImpl#verificarProdutoPorSku(UUID)}
     */
    @Test
    void testVerificarProdutoPorSku() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        when(produtoRepository.findBySku(Mockito.<UUID>any())).thenReturn(ofResult);
        Produto actualVerificarProdutoPorSkuResult = produtoServiceImpl.verificarProdutoPorSku(UUID.randomUUID());
        assertSame(produto, actualVerificarProdutoPorSkuResult);
        assertEquals("42", actualVerificarProdutoPorSkuResult.getPreco().toString());
        verify(produtoRepository).findBySku(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#verificarProdutoPorSku(UUID)}
     */
    @Test
    void testVerificarProdutoPorSku2() {
        when(produtoRepository.findBySku(Mockito.<UUID>any())).thenReturn(Optional.empty());
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.verificarProdutoPorSku(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#verificarProdutoPorSku(UUID)}
     */
    @Test
    void testVerificarProdutoPorSku3() {
        when(produtoRepository.findBySku(Mockito.<UUID>any())).thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.verificarProdutoPorSku(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#verificarProdutoPorId(Long)}
     */
    @Test
    void testVerificarProdutoPorId() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Nome");
        produto.setPreco(BigDecimal.valueOf(42L));
        produto.setSku(UUID.randomUUID());
        Optional<Produto> ofResult = Optional.of(produto);
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Produto actualVerificarProdutoPorIdResult = produtoServiceImpl.verificarProdutoPorId(1L);
        assertSame(produto, actualVerificarProdutoPorIdResult);
        assertEquals("42", actualVerificarProdutoPorIdResult.getPreco().toString());
        verify(produtoRepository).findById(Mockito.<Long>any());
    }


    /**
     * Method under test: {@link ProdutoServiceImpl#verificarProdutoPorId(Long)}
     */
    @Test
    void testVerificarProdutoPorId3() {
        when(produtoRepository.findById(Mockito.<Long>any())).thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.verificarProdutoPorId(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
    }
}

