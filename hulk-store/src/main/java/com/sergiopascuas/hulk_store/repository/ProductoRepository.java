package com.sergiopascuas.hulk_store.repository;

import com.sergiopascuas.hulk_store.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.stock > :stock AND p.categoria = :categoria")
    List<Producto> buscarPorStockYCategoria(@Param("stock") int stock, @Param("categoria") String categoria);
}