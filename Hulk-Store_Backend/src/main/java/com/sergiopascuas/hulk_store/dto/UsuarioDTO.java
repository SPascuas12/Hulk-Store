package com.sergiopascuas.hulk_store.dto;

import com.sergiopascuas.hulk_store.model.NombreRol;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioDTO {
    private String nombre;
    private String correo;
    private String password;
    @Schema(hidden = true)
    private NombreRol nombreRol;

    public UsuarioDTO(){}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public NombreRol getNombreRol() { return nombreRol; }
    public void setNombreRol(NombreRol nombreRol) { this.nombreRol = nombreRol; }
}
