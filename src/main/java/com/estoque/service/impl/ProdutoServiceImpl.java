package com.estoque.service.impl;

import com.estoque.domain.Produto;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.ProdutoRepository;
import com.estoque.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<ProdutoDTO> cadastrar(List<ProdutoDTO> produtoDTOS) {

        List<Produto> produtos = produtoDTOS.stream().map(p -> this.modelMapper.map(p, Produto.class))
                .collect(Collectors.toList());

        this.produtoRepository.saveAll(produtos);
        return produtoDTOS;
    }

    @Override
    public Produto buscarPorUUID(UUID sku) {

        Optional<Produto> produto = this.produtoRepository.findBySku(sku);
        if (produto.isPresent())
            return produto.get();

        throw new VendaNotFoundException("Produto SKU: " + sku + " não encontrado!");

    }

    @Override
    public List<ProdutoDTO> buscarTodos(int page, int size, String sort, String order) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.valueOf(sort), order);

       return this.produtoRepository.findAll(request)
                .stream().map(produto -> this.modelMapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void excluir(Long id) {
        this.produtoRepository.delete(buscarPorId(id));
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> produto = this.produtoRepository.findById(id);

        if (produto.isPresent())
            return produto.get();

        throw new VendaNotFoundException("Produto N: " + id + " não encontrado!");
    }
}
