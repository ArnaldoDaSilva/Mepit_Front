package com.mepit.repository;

import com.mepit.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para acesso aos dados de permissões.
 */
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    /**
     * Busca permissões por role/perfil
     */
    Optional<Permissao> findByRole(String role);

    /**
     * Verifica se existe permissão para um role específico
     */
    boolean existsByRole(String role);

    /**
     * Elimina permissões por role
     */
    void deleteByRole(String role);
}
