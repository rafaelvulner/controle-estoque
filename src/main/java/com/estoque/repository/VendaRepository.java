package com.estoque.repository;

import com.estoque.domain.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query(value = "FROM Venda v WHERE v.dataDaVenda = ISNULL(:date, v.dataDaVenda)")
    Page<Venda> findByDataDaVenda(@Param("date") LocalDate date, Pageable pageable);
}
