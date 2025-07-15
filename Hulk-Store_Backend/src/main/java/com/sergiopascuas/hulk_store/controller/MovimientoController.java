package com.sergiopascuas.hulk_store.controller;

import com.sergiopascuas.hulk_store.dto.MovimientoDTO;
import com.sergiopascuas.hulk_store.model.Movimiento;
import com.sergiopascuas.hulk_store.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Listar todos los movimientos
@RequestMapping("/api/movimiento")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService){
        this.movimientoService = movimientoService;
    }
    //Registra una entrada o salida
    @PostMapping
    public ResponseEntity<String> registrarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientoService.registrarMovimiento(movimientoDTO);
        return ResponseEntity.ok("Movimiento registrado exitosamente");
    }


    //Ver movimientos por producto
    @GetMapping("/producto/{productoId}")
    public List<Movimiento> obtenerProducto(@PathVariable Long productoId){
        return movimientoService.obtenerMovimientosPorProducto(productoId);
    }
}
