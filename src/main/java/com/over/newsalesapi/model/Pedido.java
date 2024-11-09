package com.over.newsalesapi.model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;

    @ManyToOne
    private Item item;

    private int quantidade;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido() {
        this.creationDate = LocalDateTime.now();
    }

    public Pedido(Item item, int quantidade, Usuario usuario, StatusPedido status) {
        this.creationDate = LocalDateTime.now();
        this.item = item;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.status = status;
    }
}
