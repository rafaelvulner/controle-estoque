package com.estoque.service;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;

import java.util.List;
import java.util.UUID;
public interface ProdutoService {
    ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO);

    ProdutoDTO buscarProduto(UUID id);

    List<ProdutoDTO> buscarTodosProdutos();

    void excluirProduto(Long id);

    Produto verificarProdutoPorId(Long id);

    Produto verificarProdutoPorSku(UUID sku);


    List<ProdutoDTO> cadastrarProdutos(List<ProdutoDTO> produtoDTOS);
}
