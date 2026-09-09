package com.mepit.controller;

import com.mepit.model.Permissao;
import com.mepit.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/permissoes")
@CrossOrigin(origins = "*")
public class PermissaoController {

    private final PermissaoService permissaoService;

    @Autowired
    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodas() {
        List<Permissao> permissoes = permissaoService.listarTodas();
        Map<String, Object> rolesMap = new HashMap<>();

        for (Permissao p : permissoes) {
            rolesMap.put(p.getRole(), buildRoleData(p));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", true);
        response.put("dados", rolesMap);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{role}")
    public ResponseEntity<Map<String, Object>> listarPorRole(@PathVariable String role) {
        Optional<Permissao> permissao = permissaoService.buscarPorRole(role);

        if (permissao.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("dados", buildRoleData(permissao.get()));
            return ResponseEntity.ok(response);
        }

        Map<String, Object> defaults = permissaoService.getRoleDefaults().get(role);
        if (defaults != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("dados", defaults);
            return ResponseEntity.ok(response);
        }

        Map<String, Object> error = new HashMap<>();
        error.put("sucesso", false);
        error.put("mensagem", "Role não encontrado: " + role);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> minhasPermissoes(
            @RequestHeader(value = "X-User-Perfil", required = false) String perfil) {

        if (perfil == null || perfil.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("dados", Collections.emptyMap());
            return ResponseEntity.ok(response);
        }

        Optional<Permissao> permissao = permissaoService.buscarPorRole(perfil);
        if (permissao.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("dados", buildRoleData(permissao.get()));
            return ResponseEntity.ok(response);
        }

        Map<String, Object> defaults = permissaoService.getRoleDefaults().get(perfil);
        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", true);
        response.put("dados", defaults != null ? defaults : Collections.emptyMap());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@RequestBody Map<String, Object> body) {
        String role = (String) body.get("role");
        Map<String, Object> alteracoes = (Map<String, Object>) body.get("alteracoes");

        if (role == null || role.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("sucesso", false);
            error.put("mensagem", "Role é obrigatório");
            return ResponseEntity.badRequest().body(error);
        }

        if (alteracoes == null) alteracoes = new HashMap<>();

        Permissao atualizada = permissaoService.atualizarPermissoes(role, alteracoes);
        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", true);
        response.put("mensagem", "Permissões atualizadas com sucesso");
        response.put("dados", buildRoleData(atualizada));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> reporPadrao(@RequestParam(required = false) String role) {
        permissaoService.reporPadrao(role);
        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", true);
        response.put("mensagem", role != null
                ? "Permissões repostas para: " + role
                : "Permissões repostas para todos os roles");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/defaults")
    public ResponseEntity<Map<String, Object>> listarDefaults() {
        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", true);
        response.put("dados", permissaoService.getRoleDefaults());
        return ResponseEntity.ok(response);
    }

    private Map<String, Object> buildRoleData(Permissao p) {
        Map<String, Object> data = new HashMap<>();
        data.put("role", p.getRole());
        data.put("globalAccess", p.getGlobalAccess());
        data.put("multiSucursal", p.getMultiSucursal());
        data.put("canViewAll", p.getCanViewAll());
        data.put("canManageUsers", p.getCanManageUsers());
        data.put("canManageSystem", p.getCanManageSystem());
        data.put("branchAccessMode", p.getBranchAccessMode());
        data.put("dashboardKpis", p.getDashboardKpis());
        data.put("dashboardAlertas", p.getDashboardAlertas());
        if (p.getMenus() != null && !p.getMenus().isEmpty()) {
            data.put("menus", Arrays.asList(p.getMenus().split(",")));
        } else {
            data.put("menus", new ArrayList<>());
        }
        return data;
    }
}
