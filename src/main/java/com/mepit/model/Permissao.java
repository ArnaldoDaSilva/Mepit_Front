package com.mepit.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entidade que representa as permissões de um role (perfil) de utilizador.
 * O administrador pode configurar quais menus e funcionalidades cada role pode aceder.
 */
@Entity
@Table(name = "permissoes")
public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Role/perfil do utilizador (ex: Administrador, Gestor, Supervisor, Operador, Técnico)
     */
    @Column(name = "role", nullable = false, unique = true, length = 50)
    private String role;

    /**
     * Lista de menus que o role pode aceder (armazenado como JSON ou CSV)
     */
    @Column(name = "menus", columnDefinition = "TEXT")
    private String menus;

    /**
     * Acesso global (pode ver todas as sucursais)
     */
    @Column(name = "global_access")
    private Boolean globalAccess = false;

    /**
     * Acesso multi-sucursal
     */
    @Column(name = "multi_sucursal")
    private Boolean multiSucursal = false;

    /**
     * Pode ver todos os registos
     */
    @Column(name = "can_view_all")
    private Boolean canViewAll = false;

    /**
     * Pode gerir utilizadores
     */
    @Column(name = "can_manage_users")
    private Boolean canManageUsers = false;

    /**
     * Pode gerir o sistema
     */
    @Column(name = "can_manage_system")
    private Boolean canManageSystem = false;

    /**
     * Política de acesso a sucursais (OWN = apenas própria, OTHER = outras, ALL = todas)
     */
    @Column(name = "branch_access_mode", length = 20)
    private String branchAccessMode = "OWN";

    /**
     * Configurações de dashboard (KPIs, alertas, etc.)
     */
    @Column(name = "dashboard_kpis")
    private Boolean dashboardKpis = true;

    @Column(name = "dashboard_alertas")
    private Boolean dashboardAlertas = true;

    /**
     * Visibilidade externa
     */
    @Column(name = "external_visibility", columnDefinition = "TEXT")
    private String externalVisibility;

    // Construtores
    public Permissao() {
    }

    public Permissao(String role, String menus) {
        this.role = role;
        this.menus = menus;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public Boolean getGlobalAccess() {
        return globalAccess;
    }

    public void setGlobalAccess(Boolean globalAccess) {
        this.globalAccess = globalAccess;
    }

    public Boolean getMultiSucursal() {
        return multiSucursal;
    }

    public void setMultiSucursal(Boolean multiSucursal) {
        this.multiSucursal = multiSucursal;
    }

    public Boolean getCanViewAll() {
        return canViewAll;
    }

    public void setCanViewAll(Boolean canViewAll) {
        this.canViewAll = canViewAll;
    }

    public Boolean getCanManageUsers() {
        return canManageUsers;
    }

    public void setCanManageUsers(Boolean canManageUsers) {
        this.canManageUsers = canManageUsers;
    }

    public Boolean getCanManageSystem() {
        return canManageSystem;
    }

    public void setCanManageSystem(Boolean canManageSystem) {
        this.canManageSystem = canManageSystem;
    }

    public String getBranchAccessMode() {
        return branchAccessMode;
    }

    public void setBranchAccessMode(String branchAccessMode) {
        this.branchAccessMode = branchAccessMode;
    }

    public Boolean getDashboardKpis() {
        return dashboardKpis;
    }

    public void setDashboardKpis(Boolean dashboardKpis) {
        this.dashboardKpis = dashboardKpis;
    }

    public Boolean getDashboardAlertas() {
        return dashboardAlertas;
    }

    public void setDashboardAlertas(Boolean dashboardAlertas) {
        this.dashboardAlertas = dashboardAlertas;
    }

    public String getExternalVisibility() {
        return externalVisibility;
    }

    public void setExternalVisibility(String externalVisibility) {
        this.externalVisibility = externalVisibility;
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", globalAccess=" + globalAccess +
                ", multiSucursal=" + multiSucursal +
                '}';
    }
}
