package com.sergiopascuas.hulk_store.controller;

import com.sergiopascuas.hulk_store.dto.JwtResponseDTO;
import com.sergiopascuas.hulk_store.dto.LoginDTO;
import com.sergiopascuas.hulk_store.dto.UsuarioDTO;
import com.sergiopascuas.hulk_store.model.Usuario;
import com.sergiopascuas.hulk_store.security.JwtUtils;
import com.sergiopascuas.hulk_store.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuarioDTO(usuarioDTO);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getCorreo(),
                        loginDTO.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getCorreo());
        String token = jwtUtils.generateToken(userDetails);

        return new JwtResponseDTO(token);
    }


}
