package com.over.newsalesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.over.newsalesapi.model.Item;
import com.over.newsalesapi.model.Pedido;
import com.over.newsalesapi.service.PedidoService;
import com.over.newsalesapi.service.StockMovementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private StockMovementService stockMovementService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedido(@PathVariable Long id) {
        return pedidoService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> obterStatusPedido(@PathVariable Long id) {
        return pedidoService.obterPorId(id)
                .map(pedido -> ResponseEntity.ok("Status do pedido: " + pedido.getStatus()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.atualizarPedido(id, pedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (pedidoService.deletarPedido(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/estoque/adicionar")
    public ResponseEntity<Void> adicionarMovimentoDeEstoque(@RequestParam Long itemId, @RequestParam int quantidade) {
        // Supondo que exista um método para obter Item por ID (adicione se necessário)
        Optional<Item> itemOptional = Optional.of(new Item()); // Substituir pelo método real para buscar o item
        itemOptional.ifPresent(item -> {
            item.setId(itemId);
            stockMovementService.adicionarMovimentoDeEstoque(item, quantidade);
        });
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
