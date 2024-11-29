package com.over.newsalesapi.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.over.newsalesapi.model.StockMovement;



@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(sm) FROM StockMovement sm WHERE sm.item.id = :itemId")
    int findEstoquePorItem(@Param("itemId") Long itemId);

    @Modifying
    @Transactional
    @Query("UPDATE StockMovement sm SET sm.quantidade = sm.quantidade - :quantidade WHERE sm.item.id = :itemId")
    void debitarEstoque(@Param("itemId") Long itemId, @Param("quantidade") int quantidade);
}

