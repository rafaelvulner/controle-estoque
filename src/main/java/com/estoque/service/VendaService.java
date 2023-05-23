package com.estoque.service;

import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.VendaDTO;

import java.time.LocalDate;
import java.util.List;

public interface VendaService {


    VendaDTO vendaDeProdutos(VendaDTO vendaDTO);

    VendaDTO buscarVenda(Long id);

    CancelamentoVendaDTO cancelarVenda(Long id);

    List<VendaDTO> buscarTodasVendasPorData(LocalDate data);







}
