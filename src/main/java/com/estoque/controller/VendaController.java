package com.estoque.controller;

import com.estoque.domain.dtos.CancelamentoVendaDTO;
import com.estoque.domain.dtos.VendaDTO;
import com.estoque.service.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;

    /**
     * Venda de produtos
     */
    @PostMapping
    public ResponseEntity<VendaDTO> cadastrar(@RequestBody VendaDTO venda) {
        return ResponseEntity.ok(this.vendaService.cadastrar(venda));
    }

    /**
     * Buscar uma venda por id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(this.vendaService.buscar(id));
    }

    /**
     * Desativa uma venda, mas ainda fica no historico e subtrai do caixa total
     */
    @PostMapping("/{id}")
    public ResponseEntity<CancelamentoVendaDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(this.vendaService.cancelar(id));
    }


}
