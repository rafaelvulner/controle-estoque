package com.estoque.domain.dtos;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.Produto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelamentoVendaDTO {

    private String mensagem;

    private Set<Produto> produtos;

    private FormaPagamentoEnum formaPagamento;
}
