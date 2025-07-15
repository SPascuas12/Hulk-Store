package com.sergiopascuas.hulk_store.dto;

import com.sergiopascuas.hulk_store.model.NombreRol;
import lombok.Getter;
import lombok.Setter;


public class UsuarioUpdateDTO {
    private String nombre;
    private String correo;
    private NombreRol nombreRol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public NombreRol getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(NombreRol nombreRol) {
        this.nombreRol = nombreRol;
    }
}

