package com.over.newsalesapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.transaction.Transactional;

import com.over.newsalesapi.service.PedidoService;
import com.over.newsalesapi.model.Item;
import com.over.newsalesapi.model.Pedido;
import com.over.newsalesapi.model.Usuario;
import com.over.newsalesapi.repository.ItemRepository;
import com.over.newsalesapi.repository.PedidoRepository;
import com.over.newsalesapi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@Transactional
@Rollback(true)
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testCriarPedido() {
        // Configuração dos mocks, conforme explicado anteriormente
        Item item = new Item();
        item.setId(1L);
        item.setName("Laptop");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setName("João Silva");

        Pedido pedido = new Pedido();
        pedido.setItem(item);
        pedido.setUsuario(usuario);
        pedido.setQuantidade(10);

        // Configurar os mocks para retornar os objetos corretos
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // Executar o teste
        Pedido novoPedido = pedidoService.criarPedido(pedido);

        // Verificações
        assertNotNull(novoPedido);
        assertEquals("Laptop", novoPedido.getItem().getName());
        assertEquals("João Silva", novoPedido.getUsuario().getName());
    }
}



