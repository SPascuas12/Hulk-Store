package com.sergiopascuas.hulk_store.controller;

import com.sergiopascuas.hulk_store.model.Producto;
import com.sergiopascuas.hulk_store.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/productos")
public class ProductoClienteController {
    private final ProductoService productoService;

    public ProductoClienteController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarTodos(){
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id){
        return productoService.ObtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Producto>> filtrarPorStockYCategoria(
            @RequestParam int stock,
            @RequestParam String categoria
    ) {
        List<Producto> productos = productoService.obtenerPorStockYCategoria(stock, categoria);
        return ResponseEntity.ok(productos);
    }
}
