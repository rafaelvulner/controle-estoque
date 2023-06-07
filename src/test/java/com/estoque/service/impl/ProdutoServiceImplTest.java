package com.estoque.service.impl;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ProdutoServiceImplTest {

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrar(List)}
     */
    @Test
    void testCadastrar() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        List<ProdutoDTO> actualCadastrarResult = produtoServiceImpl.cadastrar(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarResult);
        assertTrue(actualCadastrarResult.isEmpty());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrar(List)}
     */
    @Test
    void testCadastrar2() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        List<ProdutoDTO> actualCadastrarResult = produtoServiceImpl.cadastrar(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarResult);
        assertEquals(1, actualCadastrarResult.size());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrar(List)}
     */
    @Test
    void testCadastrar3() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        produtoDTOS.add(new ProdutoDTO());
        List<ProdutoDTO> actualCadastrarResult = produtoServiceImpl.cadastrar(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarResult);
        assertEquals(2, actualCadastrarResult.size());
        verify(produtoRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrar(List)}
     */
    @Test
    void testCadastrar4() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any()))
                .thenThrow(new VendaNotFoundException("An error occurred"));
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.cadastrar(new ArrayList<>()));
        verify(produtoRepository).saveAll(Mockito.any());
    }


    /**
     * Method under test: {@link ProdutoServiceImpl#cadastrar(List)}
     */
    @Test
    void testCadastrar6() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Produto>>any())).thenReturn(new Produto());
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, modelMapper);

        ArrayList<ProdutoDTO> produtoDTOS = new ArrayList<>();
        produtoDTOS.add(new ProdutoDTO());
        List<ProdutoDTO> actualCadastrarResult = produtoServiceImpl.cadastrar(produtoDTOS);
        assertSame(produtoDTOS, actualCadastrarResult);
        assertEquals(1, actualCadastrarResult.size());
        verify(produtoRepository).saveAll(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Produto>>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorUUID(UUID)}
     */
    @Test
    void testBuscarPorUUID() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        Produto produto = new Produto();
        when(produtoRepository.findBySku(Mockito.any())).thenReturn(Optional.of(produto));
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        assertSame(produto, produtoServiceImpl.buscarPorUUID(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorUUID(UUID)}
     */
    @Test
    void testBuscarPorUUID2() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findBySku(Mockito.any())).thenReturn(Optional.empty());
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.buscarPorUUID(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorUUID(UUID)}
     */
    @Test
    void testBuscarPorUUID3() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findBySku(Mockito.any())).thenThrow(new VendaNotFoundException("An error occurred"));
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        assertThrows(VendaNotFoundException.class, () -> produtoServiceImpl.buscarPorUUID(UUID.randomUUID()));
        verify(produtoRepository).findBySku(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarTodos(int, int, String, String)}
     */
    @Test
    void testBuscarTodos() {
        PageRequest request = PageRequest.of(1, 2, Sort.Direction.ASC, "nome");
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findAll(request)
                ).thenReturn(new PageImpl<>(new ArrayList<>()));
        ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl(produtoRepository, new ModelMapper());
        List<ProdutoDTO> produtoDTOS = produtoServiceImpl.buscarTodos(1, 2, Sort.Direction.ASC.name(), "nome");
        assertNotNull(produtoDTOS);
    }


    /**
     * Method under test: {@link ProdutoServiceImpl#excluir(Long)}
     */
    @Test
    void testExcluir() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        doNothing().when(produtoRepository).delete(Mockito.any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Produto()));
        (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).excluir(1L);
        verify(produtoRepository).findById(Mockito.<Long>any());
        verify(produtoRepository).delete(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#excluir(Long)}
     */
    @Test
    void testExcluir2() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        doThrow(new VendaNotFoundException("An error occurred")).when(produtoRepository).delete(Mockito.any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Produto()));
        assertThrows(VendaNotFoundException.class,
                () -> (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).excluir(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
        verify(produtoRepository).delete(Mockito.any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#excluir(Long)}
     */
    @Test
    void testExcluir3() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        doNothing().when(produtoRepository).delete(Mockito.any());
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(VendaNotFoundException.class,
                () -> (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).excluir(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorId(Long)}
     */
    @Test
    void testBuscarPorId() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        Produto produto = new Produto();
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(produto));
        assertSame(produto, (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).buscarPorId(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorId(Long)}
     */
    @Test
    void testBuscarPorId2() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(VendaNotFoundException.class,
                () -> (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).buscarPorId(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ProdutoServiceImpl#buscarPorId(Long)}
     */
    @Test
    void testBuscarPorId3() {

        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        when(produtoRepository.findById(Mockito.<Long>any())).thenThrow(new VendaNotFoundException("An error occurred"));
        assertThrows(VendaNotFoundException.class,
                () -> (new ProdutoServiceImpl(produtoRepository, new ModelMapper())).buscarPorId(1L));
        verify(produtoRepository).findById(Mockito.<Long>any());
    }
}

