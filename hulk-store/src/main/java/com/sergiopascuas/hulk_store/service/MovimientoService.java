package com.sergiopascuas.hulk_store.service;

import com.sergiopascuas.hulk_store.dto.MovimientoDTO;
import com.sergiopascuas.hulk_store.model.Movimiento;
import com.sergiopascuas.hulk_store.model.Producto;
import com.sergiopascuas.hulk_store.repository.MovimientoRepository;
import com.sergiopascuas.hulk_store.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, ProductoRepository productoRepository){
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    //Verificar si hay stock suficiente antes de permitir una salida.
    public void registrarMovimiento(MovimientoDTO dto) {
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (dto.getTipoMovimiento().equalsIgnoreCase("SALIDA") && producto.getStock() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para la salida");
        }

        // Actualizar stock
        int nuevoStock = dto.getTipoMovimiento().equalsIgnoreCase("ENTRADA")
                ? producto.getStock() + dto.getCantidad()
                : producto.getStock() - dto.getCantidad();
        producto.setStock(nuevoStock);

        // Crear entidad Movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setPrecioUnitario(dto.getPrecioUnitario());
        movimiento.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now());

        // Guardar
        productoRepository.save(producto);
        movimientoRepository.save(movimiento);
    }

    //Actualizar el stock del producto según la operación.
    public List<Movimiento> obtenerMovimientosPorProducto(Long productoId) {
        return movimientoRepository.findByProductoId(productoId);
    }
}

