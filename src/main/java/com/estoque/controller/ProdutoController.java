package com.estoque.controller;

import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    /**
     * Excluir um ou mais produtos
     * @return
     */
    @PostMapping
    public ResponseEntity<List<ProdutoDTO>> cadastrar(@RequestBody List<ProdutoDTO> produtos) {
        return ResponseEntity.ok(this.produtoService.cadastrar(produtos));
    }

    /**
     * Excluir apenas pelo ID, por questões de segurança, não é possível pelo SKU
     * @return Void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca produto pelo SKU
     * @return ProdutoDTO
     */
    @GetMapping("/{sku}")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable UUID sku) {
        return ResponseEntity.ok(this.modelMapper.map(this.produtoService.buscarPorUUID(sku), ProdutoDTO.class));
    }

    /**
     * Busca todos produtos cadastrados
     * @return ProdutoDTO
     */
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> buscarTodos(@RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size,
                                                        @RequestParam(required = false, defaultValue = "ASC") String sort,
                                                        @RequestParam(required = false, defaultValue = "nome") String order) {
        return ResponseEntity.ok(this.produtoService.buscarTodos(page, size, sort, order));
    }
}
