package com.over.newsalesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.over.newsalesapi.model.Item;
import com.over.newsalesapi.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> obterPorId(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    @Transactional
    public Optional<Item> atualizarItem(Long id, Item item) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return Optional.of(itemRepository.save(item));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deletarItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
