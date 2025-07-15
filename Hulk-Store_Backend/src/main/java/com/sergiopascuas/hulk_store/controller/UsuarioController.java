    package com.sergiopascuas.hulk_store.controller;

    import com.sergiopascuas.hulk_store.dto.UsuarioUpdateDTO;
    import com.sergiopascuas.hulk_store.model.Usuario;
    import com.sergiopascuas.hulk_store.service.UsuarioService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;
    import com.sergiopascuas.hulk_store.dto.UsuarioDTO;

    import java.util.List;

    @RestController
    @RequestMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public class UsuarioController {
        @Autowired
        private UsuarioService usuarioService;
        @GetMapping
        public List<Usuario> listarTodos(){
            return usuarioService.listarTodos();
        }

        @PutMapping("/{id}")
        public Usuario actualizarUsuario(@PathVariable Long id , @RequestBody UsuarioUpdateDTO usuarioDTO){
            System.out.println(">>> ACTUALIZANDO USUARIO: " + id + " con DTO: " + usuarioDTO);
            return usuarioService.actualizarUsuario(id, usuarioDTO);
        }
        @DeleteMapping("/{id}")
        public void eliminarUsuario(@PathVariable Long id) {
            usuarioService.eliminarUsuario(id);
        }
    }
