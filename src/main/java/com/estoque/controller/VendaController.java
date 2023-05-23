package com.estoque.controller;

import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.service.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    /**
     * Venda de produtos
     * @param venda
     * @return
     */
    @PostMapping("/vendaDeProduto")
    public ResponseEntity<VendaDTO> vendaDeProduto(@RequestBody VendaDTO venda) {
        return ResponseEntity.ok(this.vendaService.vendaDeProdutos(venda));
    }

    /**
     * Buscar uma venda por id
     * @param id
     * @return
     */
    @GetMapping("/buscarVenda/{id}")
    public ResponseEntity<VendaDTO> buscarVenda(@PathVariable Long id){
        return ResponseEntity.ok(this.vendaService.buscarVenda(id));
    }

    /**
     * Buscar todas vendas por data
     * @param data
     * @return
     */
    @GetMapping("/buscarTodasVendasPorData")
    public ResponseEntity<List<VendaDTO>> buscarTodasVendasPorData(@RequestParam("data") LocalDate data){
        return ResponseEntity.ok(this.vendaService.buscarTodasVendasPorData(data));
    }

    /**
     * Desativa uma venda, mas ainda fica no historico e subtrai do caixa total
     * @param id
     * @return
     */
    @PostMapping("/cancelarVenda/{id}")
    public ResponseEntity<CancelamentoVendaDTO> cancelarVenda(@PathVariable Long id) {
        return ResponseEntity.ok(this.vendaService.cancelarVenda(id));
    }


}
