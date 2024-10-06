package com.nisum.crudapi.h2.Prueba.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserResponse {
    private UUID id;
    private LocalDateTime lastLogin;
    private boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
