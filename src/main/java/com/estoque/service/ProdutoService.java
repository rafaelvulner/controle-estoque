package com.estoque.service;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;

import java.util.List;
import java.util.UUID;
public interface ProdutoService {

    Produto buscarPorUUID(UUID id);

    List<ProdutoDTO> buscarTodos(int page, int size, String sort, String order);

    void excluir(Long id);


    List<ProdutoDTO> cadastrar(List<ProdutoDTO> produtos);

    Produto buscarPorId(Long id);
}
