package com.sergiopascuas.hulk_store.service;

import com.sergiopascuas.hulk_store.dto.UsuarioDTO;
import com.sergiopascuas.hulk_store.dto.UsuarioUpdateDTO;
import com.sergiopascuas.hulk_store.model.NombreRol;
import com.sergiopascuas.hulk_store.model.Rol;
import com.sergiopascuas.hulk_store.model.Usuario;
import com.sergiopascuas.hulk_store.repository.RolRepository;
import com.sergiopascuas.hulk_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Metodo para registrar un nuevo usuario
    public Usuario registrarUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        // Obtener el rol desde la BD
        NombreRol rolSeleccionado = usuarioDTO.getNombreRol() != null
                ? usuarioDTO.getNombreRol()
                : NombreRol.CLIENTE;

        Rol rol = rolRepository.findByNombre(rolSeleccionado)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolSeleccionado));

        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }

    //Metodo para actualizar los datos de usuario
    public Usuario actualizarUsuario(Long id, UsuarioUpdateDTO dto){
        System.out.println(">>> Servicio: actualizando usuario con id " + id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());

        NombreRol rolSeleccionado = dto.getNombreRol() != null
                ? dto.getNombreRol()
                : NombreRol.CLIENTE;

        Rol rol = rolRepository.findByNombre(rolSeleccionado)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolSeleccionado));

        usuario.setRoles(new HashSet<>(List.of(rol)));
        return usuarioRepository.save(usuario);
    }

    //Metodo para eliminar un usuario por id
    public void eliminarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
