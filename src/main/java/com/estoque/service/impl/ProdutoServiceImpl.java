package com.estoque.service.impl;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.ProdutoRepository;
import com.estoque.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        produtoDTO.setSku(UUID.randomUUID());
        Produto produto = this.modelMapper.map(produtoDTO, Produto.class);

        this.produtoRepository.save(produto);

        return produtoDTO;
    }

    @Override
    public List<ProdutoDTO> cadastrarProdutos(List<ProdutoDTO> produtoDTOS) {
        produtoDTOS.forEach(p -> cadastrarProduto(p));
        return produtoDTOS;
    }

    @Override
    public ProdutoDTO buscarProduto(UUID id) {
        return this.modelMapper.map(verificarProdutoPorSku(id), ProdutoDTO.class);
    }

    @Override
    public List<ProdutoDTO> buscarTodosProdutos() {
        return this.produtoRepository.findAll()
                .stream().map(produto -> this.modelMapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void excluirProduto(Long id) {
        this.produtoRepository.delete(verificarProdutoPorId(id));
    }

    public Produto verificarProdutoPorSku(UUID sku) {

        try {
            return this.produtoRepository.findBySku(sku).get();
        } catch (Exception ex) {
            throw new VendaNotFoundException("Produto SKU: " + sku + " não encontrado!");
        }
    }

    public Produto verificarProdutoPorId(Long id) {
        Optional<Produto> produto = this.produtoRepository.findById(id);

        if (produto.isPresent())
            return produto.get();

        throw new VendaNotFoundException("Produto N: " + produto.get().getId() + " não encontrado!");
    }
}
