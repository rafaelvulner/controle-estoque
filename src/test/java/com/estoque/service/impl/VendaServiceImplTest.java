package com.estoque.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.Produto;
import com.estoque.domain.Venda;
import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.CaixaRepository;
import com.estoque.repository.VendaRepository;
import com.estoque.service.ProdutoService;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VendaServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VendaServiceImplTest {
    @MockBean
    private CaixaRepository caixaRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private VendaRepository vendaRepository;

    @Autowired
    private VendaServiceImpl vendaServiceImpl;

    /**
     * Method under test: {@link VendaServiceImpl#vendaDeProdutos(VendaDTO)}
     */
    @Test
    void testVendaDeProdutos3() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        when(vendaRepository.save(Mockito.<Venda>any())).thenReturn(venda);

        Venda venda2 = new Venda();
        venda2.setAtivo(true);
        venda2.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda2.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda2.setId(1L);
        venda2.setProdutos(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(venda2);

        Caixa caixa = new Caixa();
        caixa.setDataPagamento(LocalDate.of(1970, 1, 1));
        caixa.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        caixa.setId(1L);
        caixa.setTotal(BigDecimal.valueOf(42L));
        when(caixaRepository.save(Mockito.<Caixa>any())).thenReturn(caixa);

        ArrayList<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(new ProdutoDTO());
        VendaDTO vendaDTO = mock(VendaDTO.class);
        when(vendaDTO.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(vendaDTO.getProdutos()).thenReturn(new ArrayList<>());
        doNothing().when(vendaDTO).setProdutos(Mockito.<List<ProdutoDTO>>any());
        vendaDTO.setProdutos(produtos);
        assertSame(vendaDTO, vendaServiceImpl.vendaDeProdutos(vendaDTO));
        verify(vendaRepository).save(Mockito.<Venda>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(caixaRepository).save(Mockito.<Caixa>any());
        verify(vendaDTO).getFormaPagamento();
        verify(vendaDTO, atLeast(1)).getProdutos();
        verify(vendaDTO).setProdutos(Mockito.<List<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#vendaDeProdutos(VendaDTO)}
     */
    @Test
    void testVendaDeProdutos4() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        when(vendaRepository.save(Mockito.<Venda>any())).thenReturn(venda);

        Venda venda2 = new Venda();
        venda2.setAtivo(true);
        venda2.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda2.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda2.setId(1L);
        venda2.setProdutos(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(venda2);
        when(caixaRepository.save(Mockito.<Caixa>any())).thenThrow(new VendaNotFoundException("An error occurred"));

        ArrayList<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(new ProdutoDTO());
        VendaDTO vendaDTO = mock(VendaDTO.class);
        when(vendaDTO.getFormaPagamento()).thenReturn(FormaPagamentoEnum.DEBITO);
        when(vendaDTO.getProdutos()).thenReturn(new ArrayList<>());
        doNothing().when(vendaDTO).setProdutos(Mockito.<List<ProdutoDTO>>any());
        vendaDTO.setProdutos(produtos);
        assertThrows(VendaNotFoundException.class, () -> vendaServiceImpl.vendaDeProdutos(vendaDTO));
        verify(vendaRepository).save(Mockito.<Venda>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(caixaRepository).save(Mockito.<Caixa>any());
        verify(vendaDTO).getFormaPagamento();
        verify(vendaDTO, atLeast(1)).getProdutos();
        verify(vendaDTO).setProdutos(Mockito.<List<ProdutoDTO>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarVenda(Long)}
     */
    @Test
    void testBuscarVenda() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        Optional<Venda> ofResult = Optional.of(venda);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class, () -> vendaServiceImpl.buscarVenda(1L));
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarVenda(Long)}
     */
    @Test
    void testBuscarVenda2() {
        Venda venda = mock(Venda.class);
        doNothing().when(venda).setAtivo(anyBoolean());
        doNothing().when(venda).setDataDaVenda(Mockito.<LocalDate>any());
        doNothing().when(venda).setFormaPagamento(Mockito.<FormaPagamentoEnum>any());
        doNothing().when(venda).setId(Mockito.<Long>any());
        doNothing().when(venda).setProdutos(Mockito.<Set<Produto>>any());
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        Optional<Venda> ofResult = Optional.of(venda);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        VendaDTO vendaDTO = new VendaDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(vendaDTO);
        assertSame(vendaDTO, vendaServiceImpl.buscarVenda(1L));
        verify(vendaRepository).findById(Mockito.<Long>any());
        verify(venda).setAtivo(anyBoolean());
        verify(venda).setDataDaVenda(Mockito.<LocalDate>any());
        verify(venda).setFormaPagamento(Mockito.<FormaPagamentoEnum>any());
        verify(venda).setId(Mockito.<Long>any());
        verify(venda).setProdutos(Mockito.<Set<Produto>>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodasVendasPorData(LocalDate)}
     */
    @Test
    void testBuscarTodasVendasPorData() {
        when(vendaRepository.findByDataDaVenda(Mockito.<LocalDate>any())).thenReturn(new ArrayList<>());
        assertTrue(vendaServiceImpl.buscarTodasVendasPorData(LocalDate.of(1970, 1, 1)).isEmpty());
        verify(vendaRepository).findByDataDaVenda(Mockito.<LocalDate>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodasVendasPorData(LocalDate)}
     */
    @Test
    void testBuscarTodasVendasPorData2() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());

        ArrayList<Venda> vendaList = new ArrayList<>();
        vendaList.add(venda);
        when(vendaRepository.findByDataDaVenda(Mockito.<LocalDate>any())).thenReturn(vendaList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any())).thenReturn(new VendaDTO());
        assertEquals(1, vendaServiceImpl.buscarTodasVendasPorData(LocalDate.of(1970, 1, 1)).size());
        verify(vendaRepository).findByDataDaVenda(Mockito.<LocalDate>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodasVendasPorData(LocalDate)}
     */
    @Test
    void testBuscarTodasVendasPorData3() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());

        Venda venda2 = new Venda();
        venda2.setAtivo(false);
        venda2.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda2.setFormaPagamento(FormaPagamentoEnum.CREDITO);
        venda2.setId(2L);
        venda2.setProdutos(new HashSet<>());

        ArrayList<Venda> vendaList = new ArrayList<>();
        vendaList.add(venda2);
        vendaList.add(venda);
        when(vendaRepository.findByDataDaVenda(Mockito.<LocalDate>any())).thenReturn(vendaList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any())).thenReturn(new VendaDTO());
        assertEquals(2, vendaServiceImpl.buscarTodasVendasPorData(LocalDate.of(1970, 1, 1)).size());
        verify(vendaRepository).findByDataDaVenda(Mockito.<LocalDate>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#buscarTodasVendasPorData(LocalDate)}
     */
    @Test
    void testBuscarTodasVendasPorData4() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());

        ArrayList<Venda> vendaList = new ArrayList<>();
        vendaList.add(venda);
        when(vendaRepository.findByDataDaVenda(Mockito.<LocalDate>any())).thenReturn(vendaList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class,
                () -> vendaServiceImpl.buscarTodasVendasPorData(LocalDate.of(1970, 1, 1)));
        verify(vendaRepository).findByDataDaVenda(Mockito.<LocalDate>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<VendaDTO>>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelarVenda(Long)}
     */
    @Test
    void testCancelarVenda() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        Optional<Venda> ofResult = Optional.of(venda);

        Venda venda2 = new Venda();
        venda2.setAtivo(true);
        venda2.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda2.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda2.setId(1L);
        venda2.setProdutos(new HashSet<>());
        when(vendaRepository.save(Mockito.<Venda>any())).thenReturn(venda2);
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CancelamentoVendaDTO actualCancelarVendaResult = vendaServiceImpl.cancelarVenda(1L);
        assertEquals(FormaPagamentoEnum.DEBITO, actualCancelarVendaResult.getFormaPagamento());
        assertTrue(actualCancelarVendaResult.getProdutos().isEmpty());
        assertEquals("Venda N: 1 cancelado!", actualCancelarVendaResult.getMensagem());
        verify(vendaRepository).save(Mockito.<Venda>any());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link VendaServiceImpl#cancelarVenda(Long)}
     */
    @Test
    void testCancelarVenda2() {
        Venda venda = new Venda();
        venda.setAtivo(true);
        venda.setDataDaVenda(LocalDate.of(1970, 1, 1));
        venda.setFormaPagamento(FormaPagamentoEnum.DEBITO);
        venda.setId(1L);
        venda.setProdutos(new HashSet<>());
        Optional<Venda> ofResult = Optional.of(venda);
        when(vendaRepository.save(Mockito.<Venda>any())).thenThrow(new VendaNotFoundException("An error occurred"));
        when(vendaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(VendaNotFoundException.class, () -> vendaServiceImpl.cancelarVenda(1L));
        verify(vendaRepository).save(Mockito.<Venda>any());
        verify(vendaRepository).findById(Mockito.<Long>any());
    }

}

