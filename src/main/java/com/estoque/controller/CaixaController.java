package com.estoque.controller;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lancamento")
public class CaixaController {

    private final CaixaService caixaService;

    /**
     * Consulta o saldo diário, pode filtrar por debito, credito e data, caso não passe nenhum parametro ele traz a data corrente e todas formas de pagamento
     * @param date
     * @param formaPagamento
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ResumoDeVendasDTO>> consultar(
            @RequestParam(value = "date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate date,
            @RequestParam(value = "formaPagamento", required = false, defaultValue = "DEBITO_CREDITO") FormaPagamentoEnum formaPagamento,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "ASC") String sort,
            @RequestParam(required = false, defaultValue = "dataPagamento") String order){
        return ResponseEntity.ok(this.caixaService.consultar( date, formaPagamento, page, size, sort, order));
    }
}
