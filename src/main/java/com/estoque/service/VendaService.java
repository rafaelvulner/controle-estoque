package com.estoque.service;

import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.VendaDTO;

import java.time.LocalDate;
import java.util.List;

public interface VendaService {


    VendaDTO cadastrar(VendaDTO vendaDTO);

    VendaDTO buscar(Long id);

    CancelamentoVendaDTO cancelar(Long id);

    List<VendaDTO> buscarTodos(LocalDate localDate);









}
