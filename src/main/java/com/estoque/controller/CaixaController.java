package com.estoque.controller;

import com.estoque.domain.FormaPagamentoEnum;
import com.estoque.domain.dtos.ResumoDeVendasDTO;
import com.estoque.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class CaixaController {

    private final CaixaService caixaService;

    @GetMapping("/consultarLancamentosPorData")
    public ResponseEntity<ResumoDeVendasDTO> consultarLancamentosPorData(@RequestParam("data") LocalDate data) {
        return ResponseEntity.ok(this.caixaService.consultarLancamentosPorData(data));
    }

    @GetMapping("/consultarLancamentosPorFormaDePagamento")
    public ResponseEntity<ResumoDeVendasDTO> consultarLancamentosPorFormaDePagamento(@RequestParam("formaPagamento") FormaPagamentoEnum formaPagamento){
        return ResponseEntity.ok(this.caixaService.consultarLancamentosPorFormaDePagamento(formaPagamento));
    }

    @GetMapping("/consultarLancamentosDiario")
    public ResponseEntity<BigDecimal> consultarLancamentosDiario() {
        return ResponseEntity.ok(this.caixaService.consultarLancamentosDiario());
    }
}
