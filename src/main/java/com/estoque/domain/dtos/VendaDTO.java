package com.estoque.domain.dtos;

import com.estoque.domain.FormaPagamentoEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDTO {

    private List<ProdutoDTO> produtos;

    private FormaPagamentoEnum formaPagamento;

    private boolean ativo;
}
