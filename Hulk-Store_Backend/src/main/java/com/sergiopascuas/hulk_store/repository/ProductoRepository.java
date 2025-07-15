package com.sergiopascuas.hulk_store.repository;

import com.sergiopascuas.hulk_store.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockGreaterThanEqualAndCategoriaIgnoreCase(int stock, String categoria);
}