package com.estoque.service.impl;

import com.estoque.domain.Caixa;
import com.estoque.domain.Produto;
import com.estoque.domain.Venda;
import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.exceptions.VendaNotFoundException;
import com.estoque.repository.CaixaRepository;
import com.estoque.repository.VendaRepository;
import com.estoque.service.ProdutoService;
import com.estoque.service.VendaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

@AllArgsConstructor
@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    private final ModelMapper modelMapper;

    private final ProdutoService produtoService;

    private final CaixaRepository caixaRepository;


    @Override
    @Transactional
    public VendaDTO cadastrar(VendaDTO vendaDTO) {
        Set<Produto> produtos = new HashSet<>();
        BigDecimal total = vendaDTO.getProdutos().stream()
                .map(ProdutoDTO::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Caixa caixa = Caixa.builder()
                .dataPagamento(now())
                .formaPagamento(vendaDTO.getFormaPagamento())
                .total(total)
                .build();

        this.caixaRepository.save(caixa);

        vendaDTO.getProdutos().forEach(p -> produtos.add(this.modelMapper.map(this.produtoService.buscarPorUUID(p.getSku()), Produto.class)));

        Venda venda = this.modelMapper.map(vendaDTO, Venda.class);
        venda.setProdutos(produtos);
        venda.setDataDaVenda(now());



        this.vendaRepository.save(venda);

        return vendaDTO;
    }

    @Override
    public VendaDTO buscar(Long id) {
        return this.modelMapper.map(this.vendaRepository.findById(id), VendaDTO.class);
    }

    @Override
    public List<VendaDTO> buscarTodos(LocalDate data) {

        PageRequest request = PageRequest.of(1, 10, Sort.Direction.valueOf("ASC"), "ativo");

        LocalDate now = now();
        return this.vendaRepository.findByDataDaVenda((data == null ? now : data), request)
                .stream().map(p -> modelMapper.map(p, VendaDTO.class)).collect(Collectors.toList());

    }

    @Override
    public CancelamentoVendaDTO cancelar(Long id) {
        Optional<Venda> venda = this.vendaRepository.findById(id);
        if (venda.isPresent()) {
            venda.get().setAtivo(false);
            this.vendaRepository.save(venda.get());
            return CancelamentoVendaDTO.builder()
                    .mensagem("Venda N: " + venda.get().getId() + " cancelado!")
                    .formaPagamento(venda.get().getFormaPagamento())
                    .produtos(venda.get().getProdutos())
                    .build();
        }
        throw new VendaNotFoundException("Venda N: " + id + " não encontrada!");
    }
}
