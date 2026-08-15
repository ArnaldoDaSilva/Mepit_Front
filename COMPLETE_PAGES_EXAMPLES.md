# MEPIT WMS - IMPLEMENTAÇÃO DETALHADA DE PÁGINAS
**Exemplos Completos de Código para Cada Página**

---

## 📋 ÍNDICE
1. [Sidebar Completa](#sidebar-completa)
2. [Navbar Completa](#navbar-completa)
3. [Dashboard Completa](#dashboard-completa)
4. [Página de Produtos](#página-de-produtos)
5. [Página de Entradas](#página-de-entradas)
6. [Formulários Reutilizáveis](#formulários-reutilizáveis)

---

## 🧭 Sidebar Completa

### Sidebar.java

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Sidebar extends JPanel {
    private JPanel menuPanel;
    private String activeMenuKey = "dashboard";
    private Consumer<String> onMenuSelect;
    private List<MenuItemComponent> menuItems = new ArrayList<>();
    
    public Sidebar(Consumer<String> onMenuSelect) {
        this.onMenuSelect = onMenuSelect;
        
        setBackground(ColorScheme.BG_SIDEBAR);
        setPreferredSize(new Dimension(UIConstants.SIDEBAR_WIDTH, Integer.MAX_VALUE));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ColorScheme.SIDEBAR_BORDER));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Logo
        add(createLogo());
        
        // Menu panels
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(ColorScheme.BG_SIDEBAR);
        menuPanel.setOpaque(true);
        
        // Adicionar seções de menu
        addMenuSection("PRINCIPAL", new String[][] {
            {"fas fa-house", "Início", "dashboard"},
            {"fas fa-box", "Produtos", "produtos"},
            {"fas fa-truck-arrow-right", "Entradas", "entradas"},
            {"fas fa-arrow-left", "Saídas", "saidas"},
            {"fas fa-warehouse", "Estoque", "estoque"},
            {"fas fa-fish", "Solicitar", "solicita"}
        });
        
        addMenuSection("GESTÃO", new String[][] {
            {"fas fa-industry", "Sucursais", "sucursais"},
            {"fas fa-user-gear", "Funcionários", "funcionarios"},
            {"fas fa-clipboard-list", "Inventário", "inventario"},
            {"fas fa-truck", "Fornecedores", "fornecedores"},
            {"fas fa-handshake", "Clientes", "clientes"},
            {"fas fa-users", "Utilizadores", "utilizadores"}
        });
        
        addMenuSection("MONITORAMENTO", new String[][] {
            {"fas fa-bell", "Alertas", "alertas"},
            {"fas fa-list-check", "Logs", "logs"},
            {"fas fa-robot", "n8n", "n8n"},
            {"fas fa-screwdriver-wrench", "Técnico", "tecnico"},
            {"fas fa-chart-line", "Relatórios", "relatorios"},
            {"fas fa-gear", "Configurações", "configuracoes"}
        });
        
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setBackground(ColorScheme.BG_SIDEBAR);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(ColorScheme.BG_SIDEBAR);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(4, 0));
        
        add(scrollPane);
        
        // Footer
        add(createFooter());
    }
    
    private JPanel createLogo() {
        JPanel logo = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.PADDING_SM, UIConstants.PADDING_SM));
        logo.setBackground(ColorScheme.BG_SIDEBAR);
        logo.setMaximumSize(new Dimension(UIConstants.SIDEBAR_WIDTH, 80));
        logo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorScheme.SIDEBAR_BORDER));
        
        // Icon com gradiente
        JLabel iconLabel = new JLabel("M") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, ColorScheme.ACCENT_BLUE,
                    getWidth(), getHeight(), ColorScheme.ACCENT_CYAN
                );
                g2d.setPaint(gradient);
                
                RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
                    0, 0, getWidth() - 1, getHeight() - 1,
                    UIConstants.RADIUS_SM, UIConstants.RADIUS_SM
                );
                g2d.fill(rect);
                
                super.paintComponent(g);
            }
        };
        iconLabel.setPreferredSize(new Dimension(44, 44));
        iconLabel.setFont(FontManager.getBold(18));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        logo.add(iconLabel);
        
        // Texto
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ColorScheme.BG_SIDEBAR);
        textPanel.setOpaque(true);
        
        JLabel brandLabel = new JLabel("MEPIT");
        brandLabel.setFont(FontManager.getBold(18));
        brandLabel.setForeground(new Color(232, 237, 245));
        
        JLabel subtitleLabel = new JLabel("WHM");
        subtitleLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        subtitleLabel.setForeground(ColorScheme.ACCENT_CYAN);
        
        textPanel.add(brandLabel);
        textPanel.add(subtitleLabel);
        logo.add(textPanel);
        
        return logo;
    }
    
    private void addMenuSection(String sectionTitle, String[][] items) {
        // Título da seção
        JLabel sectionLabel = new JLabel(sectionTitle);
        sectionLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_EXTRA_TINY));
        sectionLabel.setForeground(ColorScheme.SIDEBAR_TEXT_MUTED);
        sectionLabel.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_SM,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XS,
            UIConstants.PADDING_SM
        ));
        sectionLabel.setMaximumSize(new Dimension(UIConstants.SIDEBAR_WIDTH, 25));
        menuPanel.add(sectionLabel);
        
        // Items
        for (String[] item : items) {
            MenuItemComponent menuItem = new MenuItemComponent(
                item[1],  // label
                item[2],  // key
                item[0],  // icon
                () -> selectMenuItem(item[2])
            );
            menuPanel.add(menuItem);
            menuItems.add(menuItem);
        }
    }
    
    private void selectMenuItem(String menuKey) {
        activeMenuKey = menuKey;
        
        // Atualizar estado visual
        for (MenuItemComponent item : menuItems) {
            item.setActive(item.getKey().equals(menuKey));
        }
        
        // Chamar callback
        if (onMenuSelect != null) {
            onMenuSelect.accept(menuKey);
        }
    }
    
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS));
        footer.setBackground(ColorScheme.BG_SIDEBAR);
        footer.setMaximumSize(new Dimension(UIConstants.SIDEBAR_WIDTH, 60));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ColorScheme.SIDEBAR_BORDER));
        
        // Divider
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBackground(ColorScheme.SIDEBAR_BORDER);
        separator.setForeground(ColorScheme.SIDEBAR_BORDER);
        
        // Help e Logout
        MenuItemComponent helpItem = new MenuItemComponent("Ajuda", "help", "fas fa-question", null);
        MenuItemComponent logoutItem = new MenuItemComponent("Sair", "logout", "fas fa-sign-out-alt", () -> {
            // Handle logout
            System.exit(0);
        });
        
        footer.add(Box.createVerticalGlue());
        footer.add(helpItem);
        footer.add(logoutItem);
        
        return footer;
    }
    
    // MenuItem Component
    private class MenuItemComponent extends JPanel {
        private String key;
        private boolean active = false;
        private Runnable onClickCallback;
        
        public MenuItemComponent(String label, String key, String icon, Runnable onClickCallback) {
            this.key = key;
            this.onClickCallback = onClickCallback;
            
            setBackground(ColorScheme.BG_SIDEBAR);
            setMaximumSize(new Dimension(UIConstants.SIDEBAR_WIDTH, 40));
            setPreferredSize(new Dimension(UIConstants.SIDEBAR_WIDTH, 40));
            setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_SM));
            setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.TRANSPARENT));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Icon
            JLabel iconLabel = new JLabel(getIconSymbol(icon));
            iconLabel.setForeground(ColorScheme.SIDEBAR_TEXT);
            iconLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
            add(iconLabel);
            
            // Label
            JLabel nameLabel = new JLabel(label);
            nameLabel.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
            nameLabel.setForeground(ColorScheme.SIDEBAR_TEXT);
            add(nameLabel);
            
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (!active) {
                        setBackground(ColorScheme.SIDEBAR_HOVER);
                    }
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (!active) {
                        setBackground(ColorScheme.BG_SIDEBAR);
                    }
                }
                
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (onClickCallback != null) {
                        onClickCallback.run();
                    }
                }
            });
            
            if (key.equals("dashboard")) {
                setActive(true);
            }
        }
        
        public void setActive(boolean active) {
            this.active = active;
            if (active) {
                setBackground(new Color(37, 99, 235, 30)); // rgba(37, 99, 235, 0.12)
                setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, ColorScheme.ACCENT_BLUE));
                ((JLabel) getComponent(1)).setForeground(ColorScheme.ACCENT_BLUE);
            } else {
                setBackground(ColorScheme.BG_SIDEBAR);
                setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.TRANSPARENT));
                ((JLabel) getComponent(1)).setForeground(ColorScheme.SIDEBAR_TEXT);
            }
            repaint();
        }
        
        public String getKey() {
            return key;
        }
        
        private String getIconSymbol(String icon) {
            // Mapear ícones FontAwesome para símbolos Unicode
            switch (icon) {
                case "fas fa-house": return "🏠";
                case "fas fa-box": return "📦";
                case "fas fa-truck-arrow-right": return "🚚";
                case "fas fa-warehouse": return "🏭";
                case "fas fa-fish": return "🐟";
                case "fas fa-industry": return "🏭";
                case "fas fa-user-gear": return "👤";
                case "fas fa-bell": return "🔔";
                case "fas fa-chart-line": return "📊";
                case "fas fa-gear": return "⚙️";
                case "fas fa-question": return "❓";
                case "fas fa-sign-out-alt": return "🚪";
                default: return "▪";
            }
        }
    }
}
```

---

## 🔝 Navbar Completa

### Navbar.java

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {
    private JLabel pageTitle;
    private JTextField searchField;
    private JLabel userLabel;
    private JComboBox<String> languageCombo;
    
    public Navbar() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_NAVBAR);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorScheme.BORDER_COLOR));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, UIConstants.NAVBAR_HEIGHT));
        
        // Left side
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_LG, 0));
        leftPanel.setBackground(ColorScheme.BG_NAVBAR);
        leftPanel.setOpaque(true);
        
        JButton toggleButton = new JButton("☰");
        toggleButton.setPreferredSize(new Dimension(36, 36));
        toggleButton.setBackground(ColorScheme.BG_NAVBAR);
        toggleButton.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        toggleButton.setForeground(ColorScheme.TEXT_SECONDARY);
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(toggleButton);
        
        pageTitle = new JLabel("Início");
        pageTitle.setFont(FontManager.getSemiBold(UIConstants.FONT_SUBTITLE));
        pageTitle.setForeground(ColorScheme.TEXT_PRIMARY);
        leftPanel.add(pageTitle);
        
        add(leftPanel, BorderLayout.WEST);
        
        // Center - Search
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.setBackground(ColorScheme.BG_NAVBAR);
        
        searchField = new JTextField("Pesquisar...");
        searchField.setPreferredSize(new Dimension(320, 36));
        searchField.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        searchField.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        searchField.setForeground(ColorScheme.TEXT_MUTED);
        centerPanel.add(searchField);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Right side
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, UIConstants.GAP_SM, 0));
        rightPanel.setBackground(ColorScheme.BG_NAVBAR);
        
        // Icons
        JButton notificationBtn = createIconButton("🔔", "Notificações");
        JButton settingsBtn = createIconButton("⚙", "Configurações");
        
        rightPanel.add(notificationBtn);
        rightPanel.add(settingsBtn);
        
        // Language
        languageCombo = new JComboBox<>(new String[]{"🇵🇹 PT", "🇬🇧 EN"});
        languageCombo.setPreferredSize(new Dimension(80, 36));
        languageCombo.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        rightPanel.add(languageCombo);
        
        // User profile
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, UIConstants.GAP_SM, UIConstants.PADDING_SM));
        userPanel.setBackground(ColorScheme.BG_NAVBAR);
        
        JLabel avatarLabel = new JLabel("JD");
        avatarLabel.setPreferredSize(new Dimension(32, 32));
        avatarLabel.setFont(FontManager.getBold(UIConstants.FONT_LABEL));
        avatarLabel.setForeground(Color.WHITE);
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarLabel.setVerticalAlignment(SwingConstants.CENTER);
        avatarLabel.setOpaque(true);
        avatarLabel.setBackground(ColorScheme.ACCENT_PURPLE);
        avatarLabel.setBorder(BorderFactory.createLineBorder(
            ColorScheme.ACCENT_PURPLE, UIConstants.RADIUS_SM
        ));
        userPanel.add(avatarLabel);
        
        userLabel = new JLabel("João Dias\nAdministrador");
        userLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        userLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        userPanel.add(userLabel);
        
        rightPanel.add(userPanel);
        
        add(rightPanel, BorderLayout.EAST);
    }
    
    private JButton createIconButton(String icon, String tooltip) {
        JButton btn = new JButton(icon);
        btn.setPreferredSize(new Dimension(36, 36));
        btn.setBackground(ColorScheme.BG_NAVBAR);
        btn.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        btn.setForeground(ColorScheme.TEXT_SECONDARY);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setToolTipText(tooltip);
        return btn;
    }
    
    public void setPageTitle(String title) {
        pageTitle.setText(title);
    }
}
```

---

## 📊 Dashboard Completa

### DashboardPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.KPICard;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private JPanel contentPanel;
    
    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        // Scroll panel para conteúdo
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(ColorScheme.BG_PRIMARY);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_XL,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_XL
        ));
        
        // KPI Grid
        contentPanel.add(createKPIGrid());
        contentPanel.add(Box.createVerticalStrut(UIConstants.PADDING_LG));
        
        // Charts
        contentPanel.add(createChartsSection());
        contentPanel.add(Box.createVerticalStrut(UIConstants.PADDING_LG));
        
        // Tables
        contentPanel.add(createTablesSection());
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createKPIGrid() {
        JPanel grid = new JPanel(new GridLayout(2, 4, UIConstants.GAP_MD, UIConstants.GAP_MD));
        grid.setOpaque(false);
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        // Sample KPI Cards
        grid.add(new KPICard("Produtos", "150", "+5", KPICard.KPIColor.BLUE));
        grid.add(new KPICard("Entradas", "42", "+8", KPICard.KPIColor.GREEN));
        grid.add(new KPICard("Saídas", "28", "-3", KPICard.KPIColor.ORANGE));
        grid.add(new KPICard("Estoque", "3.450", "+125", KPICard.KPIColor.CYAN));
        grid.add(new KPICard("Alertas", "5", "-2", KPICard.KPIColor.RED));
        grid.add(new KPICard("Utilizadores", "12", "0", KPICard.KPIColor.PURPLE));
        grid.add(new KPICard("Sucursais", "6", "0", KPICard.KPIColor.BLUE));
        grid.add(new KPICard("Fornecedores", "28", "+1", KPICard.KPIColor.CYAN));
        
        return grid;
    }
    
    private JPanel createChartsSection() {
        JPanel section = new JPanel(new GridLayout(1, 2, UIConstants.GAP_MD, 0));
        section.setOpaque(false);
        section.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Chart cards
        JPanel barChartCard = createChartCard("Movimentos por Dia", "Bar Chart");
        JPanel pieChartCard = createChartCard("Distribuição por Categoria", "Pie Chart");
        
        section.add(barChartCard);
        section.add(pieChartCard);
        
        return section;
    }
    
    private JPanel createChartCard(String title, String type) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(ColorScheme.BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(ColorScheme.BG_CARD);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SUBTITLE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel);
        
        card.add(header, BorderLayout.NORTH);
        
        // Content
        JLabel contentLabel = new JLabel("[" + type + " - Em desenvolvimento]");
        contentLabel.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        contentLabel.setForeground(ColorScheme.TEXT_MUTED);
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentLabel.setVerticalAlignment(SwingConstants.CENTER);
        card.add(contentLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createTablesSection() {
        JPanel section = new JPanel(new GridLayout(1, 2, UIConstants.GAP_MD, 0));
        section.setOpaque(false);
        section.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Últimas entradas
        JPanel entradasCard = createTableCard("Últimas Entradas", new String[]{"Data", "Produto", "QTD", "Status"});
        
        // Produtos críticos
        JPanel criticosCard = createTableCard("Produtos Críticos", new String[]{"Produto", "QTD", "Mínimo"});
        
        section.add(entradasCard);
        section.add(criticosCard);
        
        return section;
    }
    
    private JPanel createTableCard(String title, String[] columns) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(ColorScheme.BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(ColorScheme.BG_CARD);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SUBTITLE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel);
        
        card.add(header, BorderLayout.NORTH);
        
        // Table
        Object[][] data = {
            {"10/01/2025", "Notebook Dell", "5", "✓ Entrada"},
            {"09/01/2025", "Mouse Logitech", "20", "✓ Entrada"},
            {"08/01/2025", "Teclado Mecânico", "10", "✓ Entrada"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        table.setBackground(Color.WHITE);
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(null);
        card.add(tableScroll, BorderLayout.CENTER);
        
        return card;
    }
}
```

---

## 📦 Página de Produtos

### ProdutosPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ProdutosPanel extends JPanel {
    private JTable productTable;
    private StyledInput searchInput;
    private JButton newProductBtn;
    private JPanel formPanel;
    private boolean showingForm = false;
    
    public ProdutosPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        // Main content
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        mainContent.add(createPageHeader(), BorderLayout.NORTH);
        
        // Content
        mainContent.add(createTableSection(), BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createPageHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        
        // Left - Title
        JLabel titleLabel = new JLabel("📦 Produtos / Equipamentos");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        // Right - Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, UIConstants.GAP_MD, 0));
        buttonsPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        newProductBtn = new StyledButton("+ Novo Produto", StyledButton.ButtonStyle.PRIMARY);
        newProductBtn.addActionListener(e -> toggleForm());
        buttonsPanel.add(newProductBtn);
        
        header.add(buttonsPanel, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createTableSection() {
        JPanel section = new JPanel(new BorderLayout());
        section.setBackground(ColorScheme.BG_PRIMARY);
        section.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, 0, 0, 0));
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, 0));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        searchInput = new StyledInput("Pesquisar produtos...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JButton filterBtn = new StyledButton("Filtros", StyledButton.ButtonStyle.SECONDARY);
        filterPanel.add(filterBtn);
        
        section.add(filterPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Código", "Nome", "Categoria", "Quantidade", "Preço", "Ações"};
        Object[][] data = {
            {"P001", "Notebook Dell Inspiron", "Eletrônicos", "15", "€599,99", "✏️ 🗑️"},
            {"P002", "Mouse Logitech", "Periféricos", "50", "€29,99", "✏️ 🗑️"},
            {"P003", "Teclado Mecânico", "Periféricos", "25", "€149,99", "✏️ 🗑️"},
            {"P004", "Monitor LG 27\"", "Periféricos", "8", "€399,99", "✏️ 🗑️"},
            {"P005", "Webcam Logitech", "Periféricos", "12", "€79,99", "✏️ 🗑️"}
        };
        
        productTable = new JTable(data, columns);
        productTable.setRowHeight(40);
        productTable.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        productTable.setBackground(Color.WHITE);
        productTable.setGridColor(new Color(229, 231, 235));
        
        // Header customizado
        JTableHeader header = productTable.getTableHeader();
        header.setBackground(ColorScheme.BG_SECONDARY);
        header.setForeground(ColorScheme.TEXT_MUTED);
        header.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        section.add(scrollPane, BorderLayout.CENTER);
        
        return section;
    }
    
    private void toggleForm() {
        if (!showingForm) {
            showForm();
        } else {
            hideForm();
        }
    }
    
    private void showForm() {
        showingForm = true;
        formPanel = createProductForm();
        
        // Adicionar como overlay ou em novo painel
        JDialog dialog = new JDialog();
        dialog.setTitle("Novo Produto");
        dialog.setContentPane(formPanel);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private void hideForm() {
        showingForm = false;
    }
    
    private JPanel createProductForm() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(ColorScheme.BG_CARD);
        form.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, UIConstants.PADDING_LG,
                                                       UIConstants.PADDING_LG, UIConstants.PADDING_LG));
        
        // Título
        JLabel titleLabel = new JLabel("Novo Produto");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_SMALL));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        form.add(titleLabel);
        
        form.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Grid de campos
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, UIConstants.GAP_MD, UIConstants.GAP_MD));
        gridPanel.setBackground(ColorScheme.BG_CARD);
        
        // Campos
        gridPanel.add(createFormField("Nome", "Nome do produto"));
        gridPanel.add(createFormField("Código", "P001"));
        gridPanel.add(createFormField("Categoria", "Eletrônicos"));
        gridPanel.add(createFormField("SKU", "SKU123456"));
        gridPanel.add(createFormField("Preço", "€99,99"));
        gridPanel.add(createFormField("Quantidade", "100"));
        
        form.add(gridPanel);
        
        form.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Descrição
        form.add(new JLabel("Descrição"));
        JTextArea descArea = new JTextArea(3, 30);
        descArea.setLineWrap(true);
        form.add(new JScrollPane(descArea));
        
        form.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, UIConstants.GAP_MD, 0));
        buttonPanel.setBackground(ColorScheme.BG_CARD);
        
        JButton cancelBtn = new StyledButton("Cancelar", StyledButton.ButtonStyle.SECONDARY);
        JButton saveBtn = new StyledButton("Salvar", StyledButton.ButtonStyle.PRIMARY);
        
        buttonPanel.add(cancelBtn);
        buttonPanel.add(saveBtn);
        
        form.add(buttonPanel);
        
        return form;
    }
    
    private JPanel createFormField(String label, String placeholder) {
        JPanel field = new JPanel(new BorderLayout());
        field.setBackground(ColorScheme.BG_CARD);
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        labelComponent.setForeground(ColorScheme.TEXT_SECONDARY);
        field.add(labelComponent, BorderLayout.NORTH);
        
        StyledInput input = new StyledInput(placeholder);
        input.setPreferredSize(new Dimension(200, 36));
        field.add(input, BorderLayout.CENTER);
        
        return field;
    }
}
```

---

## 🚚 Página de Entradas

### EntradasPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class EntradasPanel extends JPanel {
    private JTable entradasTable;
    
    public EntradasPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("🚚 Entradas de Estoque");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newEntryBtn = new StyledButton("+ Nova Entrada", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newEntryBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar entradas...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JButton filterBtn = new StyledButton("Filtros", StyledButton.ButtonStyle.SECONDARY);
        filterPanel.add(filterBtn);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Data", "Número", "Fornecedor", "Produto", "Quantidade", "Armazém", "Status", "Ações"};
        Object[][] data = {
            {"15/01/2025", "ENT001", "Fornecedor XYZ", "Notebook", "10", "Armazém 1", "✓ Recebida", "👁️ ✏️"},
            {"14/01/2025", "ENT002", "Fornecedor ABC", "Mouse", "50", "Armazém 2", "✓ Recebida", "👁️ ✏️"},
            {"13/01/2025", "ENT003", "Fornecedor DEF", "Teclado", "25", "Armazém 1", "⏳ Pendente", "👁️ ✏️"}
        };
        
        entradasTable = new JTable(data, columns);
        entradasTable.setRowHeight(40);
        entradasTable.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(entradasTable);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 🔧 Formulários Reutilizáveis

### FormBuilder.java

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FormBuilder {
    public static JPanel createForm(String title, FormField[] fields) {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(ColorScheme.BG_CARD);
        form.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_LG
        ));
        
        // Título
        if (title != null && !title.isEmpty()) {
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_SMALL));
            titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
            form.add(titleLabel);
            form.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        }
        
        // Grid de campos
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, UIConstants.GAP_MD, UIConstants.GAP_MD));
        fieldsPanel.setBackground(ColorScheme.BG_CARD);
        
        for (FormField field : fields) {
            fieldsPanel.add(createField(field));
        }
        
        form.add(fieldsPanel);
        form.add(Box.createVerticalGlue());
        
        return form;
    }
    
    private static JPanel createField(FormField field) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(ColorScheme.BG_CARD);
        
        // Label
        JLabel label = new JLabel(field.label);
        label.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        label.setForeground(ColorScheme.TEXT_SECONDARY);
        fieldPanel.add(label, BorderLayout.NORTH);
        
        // Input
        JComponent input;
        if (field.type.equals("text")) {
            input = new StyledInput(field.placeholder);
        } else if (field.type.equals("textarea")) {
            input = new JTextArea(3, 20);
            ((JTextArea)input).setLineWrap(true);
        } else if (field.type.equals("select")) {
            input = new JComboBox<>(field.options);
        } else {
            input = new StyledInput(field.placeholder);
        }
        
        if (input instanceof JTextArea) {
            fieldPanel.add(new JScrollPane((JTextArea)input), BorderLayout.CENTER);
        } else {
            ((JComponent)input).setPreferredSize(new Dimension(Integer.MAX_VALUE, 36));
            fieldPanel.add((JComponent)input, BorderLayout.CENTER);
        }
        
        return fieldPanel;
    }
    
    public static class FormField {
        public String label;
        public String type;
        public String placeholder;
        public String[] options;
        
        public FormField(String label, String type, String placeholder) {
            this.label = label;
            this.type = type;
            this.placeholder = placeholder;
        }
        
        public FormField(String label, String type, String[] options) {
            this.label = label;
            this.type = type;
            this.options = options;
        }
    }
}
```

---

## 🎯 Próximos Passos

1. Copiar esses componentes para seu projeto Java
2. Ajustar imports conforme sua estrutura
3. Implementar conectividade com API
4. Adicionar tratamento de eventos
5. Testes e validações

---

**Fim da Implementação Detalhada de Páginas**
