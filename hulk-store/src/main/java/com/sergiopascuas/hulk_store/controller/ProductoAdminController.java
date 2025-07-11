package com.sergiopascuas.hulk_store.controller;

import com.sergiopascuas.hulk_store.model.Producto;
import com.sergiopascuas.hulk_store.service.ProductoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin/productos")
@PreAuthorize("hasRole('ADMIN')")
public class ProductoAdminController {
    private final ProductoService productoService;

    public ProductoAdminController(ProductoService productoService){
        this.productoService = productoService;
    }
    
    @GetMapping
    public List<Producto> listarTodos(){
        return productoService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> ObtenerPorId(@PathVariable Long id) {
        return productoService.ObtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @PostMapping
        public Producto crearProducto(@RequestBody Producto producto){
            return productoService.guardar(producto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
            return productoService.ObtenerPorId(id).map(p -> {
                producto.setId(id);
                return ResponseEntity.ok(productoService.guardar(producto));
            }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
            if (productoService.ObtenerPorId(id).isPresent()) {
                productoService.eliminar(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
}
