package com.estoque.repository;

import com.estoque.domain.Caixa;
import com.estoque.domain.FormaPagamentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    List<Caixa> findByDataPagamento(LocalDate date);

    List<Caixa> findByFormaPagamento(FormaPagamentoEnum formaPagamento);
}
