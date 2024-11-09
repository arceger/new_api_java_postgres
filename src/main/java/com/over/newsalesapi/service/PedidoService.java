package com.over.newsalesapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.over.newsalesapi.model.Pedido;
import com.over.newsalesapi.model.Item;
import com.over.newsalesapi.model.Usuario;
import com.over.newsalesapi.model.StatusPedido;
import com.over.newsalesapi.repository.PedidoRepository;
import com.over.newsalesapi.repository.StockMovementRepository;
import com.over.newsalesapi.repository.ItemRepository;
import com.over.newsalesapi.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private static final Logger logger = LogManager.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        // Carregando detalhes completos de Item e Usuario
        Item item = itemRepository.findById(pedido.getItem().getId()).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        Usuario usuario = usuarioRepository.findById(pedido.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        pedido.setItem(item);
        pedido.setUsuario(usuario);

        // Definindo a data de criação do pedido para a data/hora atual do sistema
        pedido.setCreationDate(LocalDateTime.now());

        Pedido novoPedido = pedidoRepository.save(pedido);
        verificarEstoque(novoPedido);
        return novoPedido;
    }

    public Optional<Pedido> obterPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Optional<Pedido> atualizarPedido(Long id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            pedido.setId(id);
            return Optional.of(pedidoRepository.save(pedido));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deletarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private void verificarEstoque(Pedido pedido) {
        int quantidadeNecessaria = pedido.getQuantidade();
        int estoqueDisponivel = stockMovementRepository.findEstoquePorItem(pedido.getItem().getId());

        if (estoqueDisponivel >= quantidadeNecessaria) {
            stockMovementRepository.debitarEstoque(pedido.getItem().getId(), quantidadeNecessaria);
            pedido.setStatus(StatusPedido.COMPLETO); 
            enviarEmailPedidoCompleto(pedido);
            logger.info("Pedido {} completo e email enviado para {}", pedido.getId(), pedido.getUsuario().getEmail());
        } else {
            pedido.setStatus(StatusPedido.PENDENTE); 
            logger.warn("Estoque insuficiente para atender o pedido {}: necessário {} mas disponível {}", 
                pedido.getId(), quantidadeNecessaria, estoqueDisponivel);
        }

        pedidoRepository.save(pedido);
    }

    private void enviarEmailPedidoCompleto(Pedido pedido) {

        logger.info("SEU PEDIDO FOI PROCESSADO.."+pedido.getUsuario());
        emailService.sendSimpleMessage(
            pedido.getUsuario().getEmail(),
            "Pedido Completo",
            "Seu pedido foi completado!"
            
        );
    }
}






