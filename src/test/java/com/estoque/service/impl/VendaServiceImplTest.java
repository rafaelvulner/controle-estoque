package com.estoque.service.impl;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.Produto;
import com.estoque.domain.Venda;
import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.CaixaRepository;
import com.estoque.repository.ProdutoRepository;
import com.estoque.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendaServiceImplTest {

    /**
     * Method under test: {@link VendaServiceImpl#cadastrar(VendaDTO)}
     */
    @Test
    void testCadastrar2() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        CaixaRepository caixaRepository = mock(CaixaRepository.class);
        when(caixaRepository.save(Mockito.any())).thenReturn(new Caixa());
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), caixaRepository);

        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setProdutos(new ArrayList<>());
        assertSame(vendaDTO, vendaServiceImpl.cadastrar(vendaDTO));
        verify(vendaRepository).save(Mockito.any());
        verify(caixaRepository).save(Mockito.any());
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#cadastrar(VendaDTO)}
     */
    @Test
    void testCadastrar3() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        CaixaRepository caixaRepository = mock(CaixaRepository.class);
        when(caixaRepository.save(Mockito.any())).thenThrow(new VendaNotFoundException("An error occurred"));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), caixaRepository);

        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setProdutos(new ArrayList<>());
        assertThrows(VendaNotFoundException.class, () -> vendaServiceImpl.cadastrar(vendaDTO));
        verify(caixaRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cadastrar(VendaDTO)}
     */
    @Test
    void testCadastrar5() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Venda>>any())).thenReturn(new Venda());
        CaixaRepository caixaRepository = mock(CaixaRepository.class);
        when(caixaRepository.save(Mockito.any())).thenReturn(new Caixa());
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), caixaRepository);

        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setProdutos(new ArrayList<>());
        assertSame(vendaDTO, vendaServiceImpl.cadastrar(vendaDTO));
        verify(vendaRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Venda>>any());
        verify(caixaRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscar(Long)}
     */
    @Test
    void testBuscar() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Venda()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaDTO actualBuscarResult = (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscar(1L);
        assertNull(actualBuscarResult.getDataDaVenda());
        assertFalse(actualBuscarResult.isAtivo());
        assertNull(actualBuscarResult.getProdutos());
        assertNull(actualBuscarResult.getFormaPagamento());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscar(Long)}
     */
    @Test
    void testBuscar2() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        HashSet<Produto> produtos = new HashSet<>();
        when(vendaRepository.findById(Mockito.<Long>any()))
                .thenReturn(Optional.of(new Venda(1L, FormaPagamentoEnum.DEBITO, produtos, LocalDate.of(1970, 1, 1), true)));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaDTO actualBuscarResult = (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscar(1L);
        assertEquals("1970-01-01", actualBuscarResult.getDataDaVenda().toString());
        assertTrue(actualBuscarResult.isAtivo());
        assertTrue(actualBuscarResult.getProdutos().isEmpty());
        assertEquals(FormaPagamentoEnum.DEBITO, actualBuscarResult.getFormaPagamento());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscar(Long)}
     */
    @Test
    void testBuscar3() {

        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(new HashSet<>());
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(venda));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaDTO actualBuscarResult = (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscar(1L);
        assertEquals("1970-01-01", actualBuscarResult.getDataDaVenda().toString());
        assertTrue(actualBuscarResult.isAtivo());
        assertTrue(actualBuscarResult.getProdutos().isEmpty());
        assertEquals(FormaPagamentoEnum.DEBITO, actualBuscarResult.getFormaPagamento());
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(venda).isAtivo();
        verify(venda).getFormaPagamento();
        verify(venda).getDataDaVenda();
        verify(venda).getProdutos();
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscar(Long)}
     */
    @Test
    void testBuscar4() {

        HashSet<Produto> produtoSet = new HashSet<>();
        produtoSet.add(new Produto());
        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(produtoSet);
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(venda));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaDTO actualBuscarResult = (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscar(1L);
        assertEquals("1970-01-01", actualBuscarResult.getDataDaVenda().toString());
        assertTrue(actualBuscarResult.isAtivo());
        List<ProdutoDTO> produtos = actualBuscarResult.getProdutos();
        assertEquals(1, produtos.size());
        assertEquals(FormaPagamentoEnum.DEBITO, actualBuscarResult.getFormaPagamento());
        ProdutoDTO getResult = produtos.get(0);
        assertNull(getResult.getNome());
        assertNull(getResult.getSku());
        assertNull(getResult.getPreco());
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(venda).isAtivo();
        verify(venda).getFormaPagamento();
        verify(venda).getDataDaVenda();
        verify(venda).getProdutos();
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscar(Long)}
     */
    @Test
    void testBuscar6() {

        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(new HashSet<>());
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(venda));
        ModelMapper modelMapper = mock(ModelMapper.class);
        VendaDTO vendaDTO = new VendaDTO();
        when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(vendaDTO);
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        assertSame(vendaDTO, (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscar(1L));
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        assertTrue(vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1)).isEmpty());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos2() {

        ArrayList<Venda> content = new ArrayList<>();
        content.add(new Venda());
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        List<VendaDTO> actualBuscarTodosResult = vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1));
        assertEquals(1, actualBuscarTodosResult.size());
        VendaDTO getResult = actualBuscarTodosResult.get(0);
        assertNull(getResult.getDataDaVenda());
        assertFalse(getResult.isAtivo());
        assertNull(getResult.getProdutos());
        assertNull(getResult.getFormaPagamento());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos3() {

        ArrayList<Venda> content = new ArrayList<>();
        content.add(new Venda());
        content.add(new Venda());
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        List<VendaDTO> actualBuscarTodosResult = vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1));
        assertEquals(2, actualBuscarTodosResult.size());
        VendaDTO getResult = actualBuscarTodosResult.get(0);
        assertFalse(getResult.isAtivo());
        VendaDTO getResult2 = actualBuscarTodosResult.get(1);
        assertFalse(getResult2.isAtivo());
        assertNull(getResult2.getProdutos());
        assertNull(getResult2.getFormaPagamento());
        assertNull(getResult2.getDataDaVenda());
        assertNull(getResult.getProdutos());
        assertNull(getResult.getFormaPagamento());
        assertNull(getResult.getDataDaVenda());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos5() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        assertTrue((new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).buscarTodos(null)
                .isEmpty());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos6() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        assertThrows(VendaNotFoundException.class, () -> vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1)));
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos8() {

        ArrayList<Venda> content = new ArrayList<>();
        HashSet<Produto> produtos = new HashSet<>();
        content.add(new Venda(1L, FormaPagamentoEnum.DEBITO, produtos, LocalDate.of(1970, 1, 1), true));
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        List<VendaDTO> actualBuscarTodosResult = vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1));
        assertEquals(1, actualBuscarTodosResult.size());
        VendaDTO getResult = actualBuscarTodosResult.get(0);
        assertEquals("1970-01-01", getResult.getDataDaVenda().toString());
        assertTrue(getResult.isAtivo());
        assertTrue(getResult.getProdutos().isEmpty());
        assertEquals(FormaPagamentoEnum.DEBITO, getResult.getFormaPagamento());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos9() {

        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(new HashSet<>());

        ArrayList<Venda> content = new ArrayList<>();
        content.add(venda);
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        List<VendaDTO> actualBuscarTodosResult = vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1));
        assertEquals(1, actualBuscarTodosResult.size());
        VendaDTO getResult = actualBuscarTodosResult.get(0);
        assertEquals("1970-01-01", getResult.getDataDaVenda().toString());
        assertTrue(getResult.isAtivo());
        assertTrue(getResult.getProdutos().isEmpty());
        assertEquals(FormaPagamentoEnum.DEBITO, getResult.getFormaPagamento());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        verify(venda).isAtivo();
        verify(venda).getFormaPagamento();
        verify(venda).getDataDaVenda();
        verify(venda).getProdutos();
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos10() {

        HashSet<Produto> produtoSet = new HashSet<>();
        produtoSet.add(new Produto());
        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(produtoSet);

        ArrayList<Venda> content = new ArrayList<>();
        content.add(venda);
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        List<VendaDTO> actualBuscarTodosResult = vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1));
        assertEquals(1, actualBuscarTodosResult.size());
        VendaDTO getResult = actualBuscarTodosResult.get(0);
        assertEquals("1970-01-01", getResult.getDataDaVenda().toString());
        assertTrue(getResult.isAtivo());
        List<ProdutoDTO> produtos = getResult.getProdutos();
        assertEquals(1, produtos.size());
        assertEquals(FormaPagamentoEnum.DEBITO, getResult.getFormaPagamento());
        ProdutoDTO getResult2 = produtos.get(0);
        assertNull(getResult2.getNome());
        assertNull(getResult2.getSku());
        assertNull(getResult2.getPreco());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        verify(venda).isAtivo();
        verify(venda).getFormaPagamento();
        verify(venda).getDataDaVenda();
        verify(venda).getProdutos();
        assertNull(vendaServiceImpl.buscar(1L));
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodos(LocalDate)}
     */
    @Test
    void testBuscarTodos11() {

        Venda venda = mock(Venda.class);
        when(venda.isAtivo()).thenReturn(true);
        when(venda.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(venda.getDataDaVenda()).thenReturn(LocalDate.of(1970, 1, 1));
        when(venda.getProdutos()).thenReturn(new HashSet<>());

        ArrayList<Venda> content = new ArrayList<>();
        content.add(venda);
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.findByDataDaVenda(Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(content));
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(null);
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        VendaServiceImpl vendaServiceImpl = new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class));
        assertEquals(1, vendaServiceImpl.buscarTodos(LocalDate.of(1970, 1, 1)).size());
        verify(vendaRepository).findByDataDaVenda(Mockito.any(), Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelar(Long)}
     */
    @Test
    void testCancelar() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Venda()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        CancelamentoVendaDTO actualCancelarResult = (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).cancelar(1L);
        assertNull(actualCancelarResult.getFormaPagamento());
        assertNull(actualCancelarResult.getProdutos());
        assertEquals("Venda N: null cancelado!", actualCancelarResult.getMensagem());
        verify(vendaRepository).save(Mockito.any());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelar(Long)}
     */
    @Test
    void testCancelar2() {

        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenThrow(new VendaNotFoundException("An error occurred"));
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Venda()));
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        assertThrows(VendaNotFoundException.class, () -> (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).cancelar(1L));
        verify(vendaRepository).save(Mockito.any());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelar(Long)}
     */
    @Test
    void testCancelar3() {

        Venda venda = mock(Venda.class);
        when(venda.getFormaPagamento()).thenThrow(new VendaNotFoundException("An error occurred"));
        when(venda.getId()).thenThrow(new VendaNotFoundException("An error occurred"));
        when(venda.getProdutos()).thenThrow(new VendaNotFoundException("An error occurred"));
        doThrow(new VendaNotFoundException("An error occurred")).when(venda).setAtivo(anyBoolean());
        Optional<Venda> ofResult = Optional.of(venda);
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        assertThrows(VendaNotFoundException.class, () -> (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).cancelar(1L));
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(venda).setAtivo(anyBoolean());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelar(Long)}
     */
    @Test
    void testCancelar4() {
        VendaRepository vendaRepository = mock(VendaRepository.class);
        when(vendaRepository.save(Mockito.any())).thenReturn(new Venda());
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        ModelMapper modelMapper = new ModelMapper();
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        assertThrows(VendaNotFoundException.class, () -> (new VendaServiceImpl(vendaRepository, modelMapper,
                new ProdutoServiceImpl(produtoRepository, new ModelMapper()), mock(CaixaRepository.class))).cancelar(1L));
        verify(vendaRepository).findById(Mockito.<Long>any());
    }
}

