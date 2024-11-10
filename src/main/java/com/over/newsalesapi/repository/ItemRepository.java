package com.over.newsalesapi.repository;

import com.over.newsalesapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ItemRepository extends JpaRepository<Item, Long> {
        
    }

