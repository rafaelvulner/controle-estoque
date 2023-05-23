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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    private final ModelMapper modelMapper;

    private final ProdutoService produtoService;

    private final CaixaRepository caixaRepository;


    @Override
    @Transactional
    public VendaDTO vendaDeProdutos(VendaDTO vendaDTO) {
        Set<Produto> produtos = new HashSet<>();
        Caixa caixa = Caixa.builder()
                .dataPagamento(LocalDate.now())
                .formaPagamento(vendaDTO.getFormaPagamento())
                .build();

        BigDecimal reduce = vendaDTO.getProdutos().stream()
                .map(ProdutoDTO::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        caixa.setTotal(reduce);

        vendaDTO.getProdutos().forEach(p -> {

                      produtos.add(this.produtoService.verificarProdutoPorSku(p.getSku()));
        });

        Venda venda = this.modelMapper.map(vendaDTO, Venda.class);
        venda.setProdutos(produtos);
        venda.setDataDaVenda(LocalDate.now());
        this.vendaRepository.save(venda);


        this.caixaRepository.save(caixa);
        return vendaDTO;
    }

    @Override
    public VendaDTO buscarVenda(Long id) {
        return this.modelMapper.map(this.vendaRepository.findById(id), VendaDTO.class);
    }

    @Override
    public List<VendaDTO> buscarTodasVendasPorData(LocalDate data) {
        return this.vendaRepository.findByDataDaVenda(data).stream().map(p -> this.modelMapper.map(p, VendaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CancelamentoVendaDTO cancelarVenda(Long id) {
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
        throw new VendaNotFoundException("Venda N: " + venda.get().getId() + " não encontrada!");
    }
}
