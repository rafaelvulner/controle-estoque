package com.estoque.repository;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {
    Page<Caixa> findCaixaByDataPagamentoAndFormaPagamento(LocalDate date, FormaPagamentoEnum formaPagamento, Pageable pageable);

    Page<Caixa> findCaixaByDataPagamento(LocalDate date, Pageable pageable);
}
