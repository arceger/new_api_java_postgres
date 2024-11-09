package com.over.newsalesapi.service;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(Long id) {
        super("Pedido com ID " + id + " não foi encontrado.");
    }
}
