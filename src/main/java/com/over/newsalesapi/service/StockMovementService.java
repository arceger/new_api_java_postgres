package com.over.newsalesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.over.newsalesapi.model.Item;
import com.over.newsalesapi.model.Pedido;
import com.over.newsalesapi.model.StockMovement;
import com.over.newsalesapi.repository.StockMovementRepository;

import java.time.LocalDateTime;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    @Autowired
    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public boolean verificarEstoque(Item item, int quantidadeNecessaria) {
        int estoqueDisponivel = stockMovementRepository.findEstoquePorItem(item.getId());
        return estoqueDisponivel >= quantidadeNecessaria;
    }

    @Transactional
    public void atenderPedidoComEstoque(Pedido pedido) {
        int quantidadeNecessaria = pedido.getQuantidade();
        Item item = pedido.getItem();
        stockMovementRepository.debitarEstoque(item.getId(), quantidadeNecessaria);
    }

    @Transactional
    public void adicionarMovimentoDeEstoque(Item item, int quantidade) {
        StockMovement movimento = new StockMovement();
        movimento.setItem(item);
        movimento.setQuantidade(quantidade);
        movimento.setCreationDate(LocalDateTime.now());
        stockMovementRepository.save(movimento);
    }
}
