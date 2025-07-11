package com.sergiopascuas.hulk_store.service;

import com.sergiopascuas.hulk_store.model.Producto;
import com.sergiopascuas.hulk_store.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }
    public Optional<Producto> ObtenerPorId(Long id){
        return productoRepository.findById(id);
    }
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> obtenerPorStockYCategoria(int stock, String categoria) {
        return productoRepository.findByStockGreaterThanEqualAndCategoriaIgnoreCase(stock, categoria);
    }

}
