package com.estoque.controller;

import com.estoque.domain.dtos.ProdutoDTO;
import com.estoque.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    /**
     * Cadastra produtos
     * @param produtoDTO
     * @return ProdutoDTO
     */
    @PostMapping("/cadastrarProduto")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(this.produtoService.cadastrarProduto(produtoDTO));
    }

    /**
     * Cadastra produtos
     * @param produtoDTO
     * @return ProdutoDTO
     */
    @PostMapping("/cadastrarProdutos")
    public ResponseEntity<List<ProdutoDTO>> cadastrarProdutos(@RequestBody List<ProdutoDTO> produtoDTOS) {
        return ResponseEntity.ok(this.produtoService.cadastrarProdutos(produtoDTOS));
    }

    /**
     * Excluir apenas pelo ID, por questões de segurança, não é possível pelo SKU
     * @param id
     * @return Void
     */
    @DeleteMapping("/excluirProduto/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        this.produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca produto pelo SKU
     * @param sku
     * @return ProdutoDTO
     */
    @GetMapping("/buscarProduto/{sku}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable UUID sku) {
        return ResponseEntity.ok(this.produtoService.buscarProduto(sku));
    }

    /**
     * Busca todos produtos cadastrados
     * @param
     * @return ProdutoDTO
     */
    @GetMapping("/buscarTodosProdutos")
    public ResponseEntity<List<ProdutoDTO>> buscarTodosProdutos() {
        return ResponseEntity.ok(this.produtoService.buscarTodosProdutos());
    }
}
