package com.sergiopascuas.hulk_store.service;

import com.sergiopascuas.hulk_store.dto.UsuarioDTO;
import com.sergiopascuas.hulk_store.model.Usuario;
import com.sergiopascuas.hulk_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Usuario registrarUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        return usuarioRepository.save(usuario);
    }
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
}
