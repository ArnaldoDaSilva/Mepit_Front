package com.mepit.config;

import com.mepit.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de dados.
 * Ao iniciar o sistema, cria as permissões padrão para cada role se não existirem.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final PermissaoService permissaoService;

    @Autowired
    public DataInitializer(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @Override
    public void run(String... args) {
        // Inicializa permissões padrão no arranque
        permissaoService.inicializarPermissoesPadrao();
        System.out.println("[INIT] Permissões padrão inicializadas com sucesso.");
    }
}
