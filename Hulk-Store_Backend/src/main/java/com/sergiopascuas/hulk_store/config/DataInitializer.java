package com.sergiopascuas.hulk_store.config;

import com.sergiopascuas.hulk_store.model.NombreRol;
import com.sergiopascuas.hulk_store.model.Rol;
import com.sergiopascuas.hulk_store.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RolRepository rolRepository;

    @Autowired
    public DataInitializer(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (rolRepository.findByNombre(NombreRol.ADMIN).isEmpty()) {
            rolRepository.save(new Rol(NombreRol.ADMIN));
        }

        if (rolRepository.findByNombre(NombreRol.CLIENTE).isEmpty()) {
            rolRepository.save(new Rol(NombreRol.CLIENTE));
        }
    }
}
