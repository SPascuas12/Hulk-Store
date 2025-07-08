package com.sergiopascuas.hulk_store.controller;

import com.sergiopascuas.hulk_store.model.Producto;
import com.sergiopascuas.hulk_store.model.Usuario;
import com.sergiopascuas.hulk_store.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sergiopascuas.hulk_store.dto.UsuarioDTO;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/registrar")
    private Usuario registrar(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.registrarUsuarioDTO(usuarioDTO);
    }
    @GetMapping
    public List<Usuario> listarTodos(){
        return usuarioService.listarTodos();
    }
}
