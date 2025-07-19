package edu.corhuila.unitrack.infrastructure.security;

import edu.corhuila.unitrack.domain.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No hay usuario autenticado.");
        }

        return (User) authentication.getPrincipal();
    }

    public Long getAuthenticatedStudentId() {
        User user = getAuthenticatedUser();

        if (user.getStudent() == null) {
            throw new RuntimeException("El usuario no tiene asociado un estudiante.");
        }

        return user.getStudent().getId();
    }
}