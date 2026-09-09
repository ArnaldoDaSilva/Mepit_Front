package com.mepit.service;

import com.mepit.model.Permissao;
import com.mepit.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;
    private static final Map<String, List<String>> ROLE_MENUS = new HashMap<>();
    private static final Map<String, Map<String, Object>> ROLE_SETTINGS = new HashMap<>();

    static {
        // Administrador
        ROLE_MENUS.put("Administrador", Arrays.asList("dashboard","produtos","entradas","saidas","estoque","inventario","fornecedores","clientes","projetos","sucursais","funcionarios","utilizadores","alertas","logs","relatorios","configuracoes","n8n","tecnico"));
        Map<String, Object> admin = new HashMap<>();
        admin.put("globalAccess", true); admin.put("multiSucursal", true); admin.put("canViewAll", true);
        admin.put("canManageUsers", true); admin.put("canManageSystem", true); admin.put("branchAccessMode", "ALL");
        ROLE_SETTINGS.put("Administrador", admin);

        // Gestor
        ROLE_MENUS.put("Gestor", Arrays.asList("dashboard","produtos","entradas","saidas","estoque","inventario","fornecedores","clientes","projetos","sucursais","funcionarios","alertas","logs","relatorios"));
        Map<String, Object> gestor = new HashMap<>();
        gestor.put("globalAccess", true); gestor.put("multiSucursal", false); gestor.put("canViewAll", false);
        gestor.put("canManageUsers", false); gestor.put("canManageSystem", false); gestor.put("branchAccessMode", "ALL");
        ROLE_SETTINGS.put("Gestor", gestor);

        // Supervisor
        ROLE_MENUS.put("Supervisor", Arrays.asList("dashboard","produtos","entradas","saidas","estoque","inventario","projetos","alertas","relatorios"));
        Map<String, Object> sup = new HashMap<>();
        sup.put("globalAccess", true); sup.put("multiSucursal", true); sup.put("canViewAll", false);
        sup.put("canManageUsers", false); sup.put("canManageSystem", true); sup.put("branchAccessMode", "ALL");
        ROLE_SETTINGS.put("Supervisor", sup);

        // Operador
        ROLE_MENUS.put("Operador", Arrays.asList("dashboard","produtos","entradas","saidas","estoque","inventario","projetos","alertas"));
        Map<String, Object> op = new HashMap<>();
        op.put("globalAccess", false); op.put("multiSucursal", false); op.put("canViewAll", false);
        op.put("canManageUsers", false); op.put("canManageSystem", false); op.put("branchAccessMode", "OWN");
        ROLE_SETTINGS.put("Operador", op);

        // Técnico
        ROLE_MENUS.put("Técnico", Arrays.asList("dashboard","alertas","tecnico","solicitacoes","devolucoes","manutencao"));
        Map<String, Object> tec = new HashMap<>();
        tec.put("globalAccess", false); tec.put("multiSucursal", false); tec.put("canViewAll", false);
        tec.put("canManageUsers", false); tec.put("canManageSystem", false); tec.put("branchAccessMode", "OWN");
        ROLE_SETTINGS.put("Técnico", tec);
    }
    public List<Permissao> listarTodas() {
        return permissaoRepository.findAll();
    }

    public Optional<Permissao> buscarPorRole(String role) {
        return permissaoRepository.findByRole(role);
    }

    @Transactional
    public Permissao atualizarPermissoes(String role, Map<String, Object> alteracoes) {
        Permissao permissao = permissaoRepository.findByRole(role).orElse(new Permissao());
        permissao.setRole(role);
        if (alteracoes.containsKey("menus")) {
            List<?> menus = (List<?>) alteracoes.get("menus");
            permissao.setMenus(String.join(",", menus.stream().map(Object::toString).toList()));
        }
        if (alteracoes.containsKey("globalAccess")) permissao.setGlobalAccess((Boolean) alteracoes.get("globalAccess"));
        if (alteracoes.containsKey("multiSucursal")) permissao.setMultiSucursal((Boolean) alteracoes.get("multiSucursal"));
        if (alteracoes.containsKey("canViewAll")) permissao.setCanViewAll((Boolean) alteracoes.get("canViewAll"));
        if (alteracoes.containsKey("canManageUsers")) permissao.setCanManageUsers((Boolean) alteracoes.get("canManageUsers"));
        if (alteracoes.containsKey("canManageSystem")) permissao.setCanManageSystem((Boolean) alteracoes.get("canManageSystem"));
        if (alteracoes.containsKey("branchAccessMode")) permissao.setBranchAccessMode((String) alteracoes.get("branchAccessMode"));
        return permissaoRepository.save(permissao);
    }

    @Transactional
    public void reporPadrao(String role) {
        if (role != null && !role.isEmpty()) {
            Map<String, Object> defaults = ROLE_SETTINGS.get(role);
            List<String> menus = ROLE_MENUS.get(role);
            if (defaults != null) {
                Permissao p = permissaoRepository.findByRole(role).orElse(new Permissao());
                p.setRole(role);
                p.setGlobalAccess((Boolean) defaults.get("globalAccess"));
                p.setMultiSucursal((Boolean) defaults.get("multiSucursal"));
                p.setCanViewAll((Boolean) defaults.get("canViewAll"));
                p.setCanManageUsers((Boolean) defaults.get("canManageUsers"));
                p.setCanManageSystem((Boolean) defaults.get("canManageSystem"));
                p.setBranchAccessMode((String) defaults.get("branchAccessMode"));
                p.setMenus(String.join(",", menus));
                permissaoRepository.save(p);
            }
        } else {
            ROLE_SETTINGS.forEach((roleName, defaults) -> {
                if (!"Administrador".equals(roleName)) {
                    List<String> menus = ROLE_MENUS.get(roleName);
                    Permissao p = permissaoRepository.findByRole(roleName).orElse(new Permissao());
                    p.setRole(roleName);
                    p.setGlobalAccess((Boolean) defaults.get("globalAccess"));
                    p.setMultiSucursal((Boolean) defaults.get("multiSucursal"));
                    p.setCanViewAll((Boolean) defaults.get("canViewAll"));
                    p.setCanManageUsers((Boolean) defaults.get("canManageUsers"));
                    p.setCanManageSystem((Boolean) defaults.get("canManageSystem"));
                    p.setBranchAccessMode((String) defaults.get("branchAccessMode"));
                    p.setMenus(String.join(",", menus));
                    permissaoRepository.save(p);
                }
            });
        }
    }

    @Transactional
    public void inicializarPermissoesPadrao() {
        ROLE_SETTINGS.forEach((roleName, defaults) -> {
            if (!permissaoRepository.existsByRole(roleName)) {
                List<String> menus = ROLE_MENUS.get(roleName);
                Permissao p = new Permissao();
                p.setRole(roleName);
                p.setGlobalAccess((Boolean) defaults.get("globalAccess"));
                p.setMultiSucursal((Boolean) defaults.get("multiSucursal"));
                p.setCanViewAll((Boolean) defaults.get("canViewAll"));
                p.setCanManageUsers((Boolean) defaults.get("canManageUsers"));
                p.setCanManageSystem((Boolean) defaults.get("canManageSystem"));
                p.setBranchAccessMode((String) defaults.get("branchAccessMode"));
                p.setMenus(String.join(",", menus));
                permissaoRepository.save(p);
            }
        });
    }

    public Map<String, Map<String, Object>> getRoleDefaults() {
        return Collections.unmodifiableMap(ROLE_SETTINGS);
    }

    @Autowired
    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }
}
